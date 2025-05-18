using espe.edu.ec.monster.controlador;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace espe.edu.ec.monster.test
{
    [TestClass]
    public class ConversionServiceTests
    {
        private ConversionService _conversionService;

        [TestInitialize]
        public void Setup()
        {
            _conversionService = new ConversionService();
        }

        [TestMethod]
        public void PulgadasACentimetros_ConValor1_Devuelve2Punto54()
        {
            double result = _conversionService.pulgadasACentimetros(1.0);
            Assert.AreEqual(2.54, result, 0.001);
        }

        [TestMethod]
        public void CentimetrosAPulgadas_ConValor2Punto54_Devuelve1()
        {
            double result = _conversionService.centimetrosAPulgadas(2.54);
            Assert.AreEqual(1.0, result, 0.001);
        }

        [TestMethod]
        public void MetrosAPies_ConValor1_Devuelve3Punto28084()
        {
            double result = _conversionService.metrosAPies(1.0);
            Assert.AreEqual(3.28084, result, 0.001);
        }

        [TestMethod]
        public void PiesAMetros_ConValor3Punto28084_Devuelve1()
        {
            double result = _conversionService.piesAMetros(3.28084);
            Assert.AreEqual(1.0, result, 0.001);
        }

        [TestMethod]
        public void MetrosAYardas_ConValor1_Devuelve1Punto09361()
        {
            double result = _conversionService.metrosAYardas(1.0);
            Assert.AreEqual(1.09361, result, 0.001);
        }

        [TestMethod]
        public void YardasAMetros_ConValor1Punto09361_Devuelve1()
        {
            double result = _conversionService.yardasAMetros(1.09361);
            Assert.AreEqual(1.0, result, 0.001);
        }

        [TestMethod]
        public void Login_ConCredencialesCorrectas_DevuelveTrue()
        {
            bool result = _conversionService.Login("MONSTER", "MONSTER9");
            Assert.IsTrue(result);
        }

        [TestMethod]
        public void Login_ConCredencialesIncorrectas_DevuelveFalse()
        {
            bool result = _conversionService.Login("INVALID", "INVALID");
            Assert.IsFalse(result);
        }
    }
}