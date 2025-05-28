using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;

namespace EurekaBankService.DB
{
    public class DatabaseAccess
    {
        private static readonly string ConnectionString = "Server=localhost,1433;Database=EUREKABANK;User Id=sa;Password=Admin123!;TrustServerCertificate=True;";

        private DatabaseAccess() { }

        public static SqlConnection GetConnection()
        {
            try
            {
                SqlConnection conn = new SqlConnection(ConnectionString);
                conn.Open();
                return conn;
            }
            catch (Exception ex)
            {
                throw new Exception("Error al conectar a la base de datos: " + ex.Message);
            }
        }
    }
}
