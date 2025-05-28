using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using EurekaBankService.DB;
using EurekaBankService.Models;

namespace EurekaBankService
{
    public class EurekaBankService : IEurekaBankService
    {
        public List<Movimiento> LeerMovimientos(string cuenta)
        {
            var lista = new List<Movimiento>();
            string sql = "SELECT m.chr_cuencodigo, m.int_movinumero, m.dtt_movifecha, m.chr_tipocodigo, " +
                         "t.vch_tipoaccion, m.dec_moviimporte " +
                         "FROM Movimiento m JOIN TipoMovimiento t ON m.chr_tipocodigo = t.chr_tipocodigo " +
                         "WHERE m.chr_cuencodigo = @Cuenta";

            using (SqlConnection conn = DatabaseAccess.GetConnection())
            {
                using (SqlCommand cmd = new SqlCommand(sql, conn))
                {
                    cmd.Parameters.AddWithValue("@Cuenta", cuenta);
                    using (SqlDataReader reader = cmd.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            lista.Add(new Movimiento
                            {
                                Cuenta = reader["chr_cuencodigo"].ToString(),
                                NroMov = Convert.ToInt32(reader["int_movinumero"]),
                                Fecha = Convert.ToDateTime(reader["dtt_movifecha"]),
                                Tipo = reader["chr_tipocodigo"].ToString(),
                                Accion = reader["vch_tipoaccion"].ToString(),
                                Importe = Convert.ToDouble(reader["dec_moviimporte"])
                            });
                        }
                    }
                }
            }
            return lista;
        }

        public int RegistrarDeposito(string cuenta, double importe, string codEmp)
        {
            try
            {
                using (SqlConnection conn = DatabaseAccess.GetConnection())
                {
                    conn.Open();
                    using (SqlTransaction transaction = conn.BeginTransaction())
                    {
                        // Validar cuenta
                        string sqlValidate = "SELECT dec_cuensaldo, chr_monecodigo FROM Cuenta WHERE chr_cuencodigo = @Cuenta AND vch_cuenestado = 'ACTIVO'";
                        using (SqlCommand cmd = new SqlCommand(sqlValidate, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@Cuenta", cuenta);
                            using (SqlDataReader reader = cmd.ExecuteReader())
                            {
                                if (!reader.Read())
                                    throw new Exception("Cuenta no válida o inactiva.");
                            }
                        }

                        // Obtener costo
                        string sqlCost = "SELECT dec_costimporte FROM CostoMovimiento WHERE chr_monecodigo = (SELECT chr_monecodigo FROM Cuenta WHERE chr_cuencodigo = @Cuenta)";
                        double costo = 0;
                        using (SqlCommand cmd = new SqlCommand(sqlCost, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@Cuenta", cuenta);
                            using (SqlDataReader reader = cmd.ExecuteReader())
                            {
                                if (reader.Read())
                                    costo = Convert.ToDouble(reader["dec_costimporte"]);
                            }
                        }

                        // Obtener próximo número de movimiento
                        string sqlMaxMov = "SELECT COALESCE(MAX(int_movinumero), 0) + 1 FROM Movimiento WHERE chr_cuencodigo = @Cuenta";
                        int moviNumero = 0;
                        using (SqlCommand cmd = new SqlCommand(sqlMaxMov, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@Cuenta", cuenta);
                            moviNumero = (int)cmd.ExecuteScalar();
                        }

                        // Registrar depósito
                        string sqlInsertMov = "INSERT INTO Movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) " +
                                             "VALUES (@Cuenta, @NroMov, GETDATE(), @CodEmp, '003', @Importe, NULL)";
                        using (SqlCommand cmd = new SqlCommand(sqlInsertMov, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@Cuenta", cuenta);
                            cmd.Parameters.AddWithValue("@NroMov", moviNumero);
                            cmd.Parameters.AddWithValue("@CodEmp", codEmp);
                            cmd.Parameters.AddWithValue("@Importe", importe);
                            cmd.ExecuteNonQuery();
                        }

                        // Registrar costo si aplica
                        if (costo > 0)
                        {
                            string sqlInsertCost = "INSERT INTO Movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) " +
                                                  "VALUES (@Cuenta, @NroMov + 1, GETDATE(), @CodEmp, '010', @Costo, NULL)";
                            using (SqlCommand cmd = new SqlCommand(sqlInsertCost, conn, transaction))
                            {
                                cmd.Parameters.AddWithValue("@Cuenta", cuenta);
                                cmd.Parameters.AddWithValue("@NroMov", moviNumero);
                                cmd.Parameters.AddWithValue("@CodEmp", codEmp);
                                cmd.Parameters.AddWithValue("@Costo", costo);
                                cmd.ExecuteNonQuery();
                            }
                        }

                        // Actualizar saldo
                        string sqlUpdate = "UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo + @Importe - @Costo, int_cuencontmov = int_cuencontmov + @MovCount WHERE chr_cuencodigo = @Cuenta";
                        using (SqlCommand cmd = new SqlCommand(sqlUpdate, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@Importe", importe);
                            cmd.Parameters.AddWithValue("@Costo", costo);
                            cmd.Parameters.AddWithValue("@MovCount", costo > 0 ? 2 : 1);
                            cmd.Parameters.AddWithValue("@Cuenta", cuenta);
                            cmd.ExecuteNonQuery();
                        }

                        transaction.Commit();
                        return 1;
                    }
                }
            }
            catch (Exception)
            {
                return -1;
            }
        }

        public int RegistrarRetiro(string cuenta, double importe, string codEmp)
        {
            try
            {
                using (SqlConnection conn = DatabaseAccess.GetConnection())
                {
                    conn.Open();
                    using (SqlTransaction transaction = conn.BeginTransaction())
                    {
                        // Validar cuenta y saldo
                        string sqlValidate = "SELECT dec_cuensaldo, chr_monecodigo FROM Cuenta WHERE chr_cuencodigo = @Cuenta AND vch_cuenestado = 'ACTIVO'";
                        using (SqlCommand cmd = new SqlCommand(sqlValidate, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@Cuenta", cuenta);
                            using (SqlDataReader reader = cmd.ExecuteReader())
                            {
                                if (!reader.Read())
                                    throw new Exception("Cuenta no válida o inactiva.");
                                double saldo = Convert.ToDouble(reader["dec_cuensaldo"]);
                                string sqlCost = "SELECT dec_costimporte FROM CostoMovimiento WHERE chr_monecodigo = @Moneda";
                                using (SqlCommand cmdCost = new SqlCommand(sqlCost, conn, transaction))
                                {
                                    cmdCost.Parameters.AddWithValue("@Moneda", reader["chr_monecodigo"]);
                                    using (SqlDataReader costReader = cmdCost.ExecuteReader())
                                    {
                                        double costo = costReader.Read() ? Convert.ToDouble(costReader["dec_costimporte"]) : 0.0;
                                        if (saldo < importe + costo)
                                            throw new Exception("Saldo insuficiente.");
                                    }
                                }
                            }
                        }

                        // Obtener próximo número de movimiento
                        string sqlMaxMov = "SELECT COALESCE(MAX(int_movinumero), 0) + 1 FROM Movimiento WHERE chr_cuencodigo = @Cuenta";
                        int moviNumero = 0;
                        using (SqlCommand cmd = new SqlCommand(sqlMaxMov, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@Cuenta", cuenta);
                            moviNumero = (int)cmd.ExecuteScalar();
                        }

                        // Registrar retiro
                        string sqlInsertMov = "INSERT INTO Movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) " +
                                             "VALUES (@Cuenta, @NroMov, GETDATE(), @CodEmp, '004', @Importe, NULL)";
                        using (SqlCommand cmd = new SqlCommand(sqlInsertMov, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@Cuenta", cuenta);
                            cmd.Parameters.AddWithValue("@NroMov", moviNumero);
                            cmd.Parameters.AddWithValue("@CodEmp", codEmp);
                            cmd.Parameters.AddWithValue("@Importe", importe);
                            cmd.ExecuteNonQuery();
                        }

                        // Actualizar saldo
                        string sqlUpdate = "UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo - @Importe, int_cuencontmov = int_cuencontmov + 1 WHERE chr_cuencodigo = @Cuenta";
                        using (SqlCommand cmd = new SqlCommand(sqlUpdate, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@Importe", importe);
                            cmd.Parameters.AddWithValue("@Cuenta", cuenta);
                            cmd.ExecuteNonQuery();
                        }

                        transaction.Commit();
                        return 1;
                    }
                }
            }
            catch (Exception)
            {
                return -1;
            }
        }

        public int RealizarTransferencia(string cuentaOrigen, string cuentaDestino, double importe, string codEmp)
        {
            try
            {
                using (SqlConnection conn = DatabaseAccess.GetConnection())
                {
                    conn.Open();
                    using (SqlTransaction transaction = conn.BeginTransaction())
                    {
                        // Validar cuentas
                        string sqlValidate = "SELECT chr_cuencodigo, dec_cuensaldo, chr_monecodigo FROM Cuenta WHERE chr_cuencodigo IN (@CuentaOrigen, @CuentaDestino) AND vch_cuenestado = 'ACTIVO'";
                        using (SqlCommand cmd = new SqlCommand(sqlValidate, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@CuentaOrigen", cuentaOrigen);
                            cmd.Parameters.AddWithValue("@CuentaDestino", cuentaDestino);
                            using (SqlDataReader reader = cmd.ExecuteReader())
                            {
                                Dictionary<string, double> saldos = new Dictionary<string, double>();
                                Dictionary<string, string> monedas = new Dictionary<string, string>();
                                while (reader.Read())
                                {
                                    saldos[reader["chr_cuencodigo"].ToString()] = Convert.ToDouble(reader["dec_cuensaldo"]);
                                    monedas[reader["chr_cuencodigo"].ToString()] = reader["chr_monecodigo"].ToString();
                                }
                                if (!saldos.ContainsKey(cuentaOrigen) || !saldos.ContainsKey(cuentaDestino))
                                    throw new Exception("Cuenta(s) no válida(s) o inactiva(s).");
                                if (!monedas[cuentaOrigen].Equals(monedas[cuentaDestino]))
                                    throw new Exception("Las cuentas deben usar la misma moneda.");
                                if (saldos[cuentaOrigen] < importe)
                                    throw new Exception("Saldo insuficiente en cuenta origen.");
                            }
                        }

                        // Obtener números de movimiento
                        string sqlMaxMov = "SELECT COALESCE(MAX(int_movinumero), 0) + 1 FROM Movimiento WHERE chr_cuencodigo = @Cuenta";
                        int moviOrigen = 0, moviDestino = 0;
                        using (SqlCommand cmd = new SqlCommand(sqlMaxMov, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@Cuenta", cuentaOrigen);
                            moviOrigen = (int)cmd.ExecuteScalar();
                        }
                        using (SqlCommand cmd = new SqlCommand(sqlMaxMov, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@Cuenta", cuentaDestino);
                            moviDestino = (int)cmd.ExecuteScalar();
                        }

                        // Registrar movimientos
                        string sqlInsertMovOrigen = "INSERT INTO Movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) " +
                                                    "VALUES (@CuentaOrigen, @NroMovOrigen, GETDATE(), @CodEmp, '009', @Importe, @CuentaDestino)";
                        using (SqlCommand cmd = new SqlCommand(sqlInsertMovOrigen, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@CuentaOrigen", cuentaOrigen);
                            cmd.Parameters.AddWithValue("@NroMovOrigen", moviOrigen);
                            cmd.Parameters.AddWithValue("@CodEmp", codEmp);
                            cmd.Parameters.AddWithValue("@Importe", importe);
                            cmd.Parameters.AddWithValue("@CuentaDestino", cuentaDestino);
                            cmd.ExecuteNonQuery();
                        }

                        string sqlInsertMovDestino = "INSERT INTO Movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) " +
                                                     "VALUES (@CuentaDestino, @NroMovDestino, GETDATE(), @CodEmp, '008', @Importe, @CuentaOrigen)";
                        using (SqlCommand cmd = new SqlCommand(sqlInsertMovDestino, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@CuentaDestino", cuentaDestino);
                            cmd.Parameters.AddWithValue("@NroMovDestino", moviDestino);
                            cmd.Parameters.AddWithValue("@CodEmp", codEmp);
                            cmd.Parameters.AddWithValue("@Importe", importe);
                            cmd.Parameters.AddWithValue("@CuentaOrigen", cuentaOrigen);
                            cmd.ExecuteNonQuery();
                        }

                        // Actualizar saldos
                        string sqlUpdateOrigen = "UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo - @Importe, int_cuencontmov = int_cuencontmov + 1 WHERE chr_cuencodigo = @CuentaOrigen";
                        using (SqlCommand cmd = new SqlCommand(sqlUpdateOrigen, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@Importe", importe);
                            cmd.Parameters.AddWithValue("@CuentaOrigen", cuentaOrigen);
                            cmd.ExecuteNonQuery();
                        }

                        string sqlUpdateDestino = "UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo + @Importe, int_cuencontmov = int_cuencontmov + 1 WHERE chr_cuencodigo = @CuentaDestino";
                        using (SqlCommand cmd = new SqlCommand(sqlUpdateDestino, conn, transaction))
                        {
                            cmd.Parameters.AddWithValue("@Importe", importe);
                            cmd.Parameters.AddWithValue("@CuentaDestino", cuentaDestino);
                            cmd.ExecuteNonQuery();
                        }

                        transaction.Commit();
                        return 1;
                    }
                }
            }
            catch (Exception)
            {
                return -1;
            }
        }

        public double VerificarSaldo(string cuenta)
        {
            try
            {
                using (SqlConnection conn = DatabaseAccess.GetConnection())
                {
                    string sql = "SELECT dec_cuensaldo FROM Cuenta WHERE chr_cuencodigo = @Cuenta AND vch_cuenestado = 'ACTIVO'";
                    using (SqlCommand cmd = new SqlCommand(sql, conn))
                    {
                        cmd.Parameters.AddWithValue("@Cuenta", cuenta);
                        object result = cmd.ExecuteScalar();
                        return result != null ? Convert.ToDouble(result) : -1.0;
                    }
                }
            }
            catch (Exception)
            {
                return -1.0;
            }
        }

        public double ObtenerCostoMovimiento(string cuenta)
        {
            try
            {
                using (SqlConnection conn = DatabaseAccess.GetConnection())
                {
                    string sql = "SELECT cm.dec_costimporte " +
                                 "FROM Cuenta c JOIN CostoMovimiento cm ON c.chr_monecodigo = cm.chr_monecodigo " +
                                 "WHERE c.chr_cuencodigo = @Cuenta AND c.vch_cuenestado = 'ACTIVO'";
                    using (SqlCommand cmd = new SqlCommand(sql, conn))
                    {
                        cmd.Parameters.AddWithValue("@Cuenta", cuenta);
                        object result = cmd.ExecuteScalar();
                        return result != null ? Convert.ToDouble(result) : -1.0;
                    }
                }
            }
            catch (Exception)
            {
                return -1.0;
            }
        }

        public Usuario IniciarSesion(string username, string clave)
        {
            if (username == "MONSTER" && clave == "MONSTER9")
            {
                return new Usuario
                {
                    Codigo = "9999",
                    NombreUsuario = "MONSTER",
                    Clave = "MONSTER9",
                    Estado = "ACTIVO"
                };
            }
            return null;
        }
    }
}