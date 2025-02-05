package com.nttdata.steps;

import com.nttdata.page.CartPage;
import com.nttdata.page.HomePage;
import com.nttdata.page.LoginPage;
import com.nttdata.page.ProductPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ProductStoreSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;

    @Before
    public void setup() {
        // Configuración del WebDriver (ajusta la ruta al chromedriver)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        // Configuración de espera implícita (además se usan esperas explícitas en los Page Objects)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Dado("estoy en la página de la tienda")
    public void estoy_en_la_pagina_de_la_tienda() {
        driver.get("https://qalab.bensg.com/store");
    }

    @Y("me logueo con mi usuario {string} y clave {string}")
    public void me_logueo_con_mi_usuario_y_clave(String usuario, String clave) {
        loginPage.login(usuario, clave);
    }

    @Cuando("navego a la categoria {string} y subcategoria {string}")
    public void navego_a_la_categoria_y_subcategoria(String categoria, String subcategoria) {
        boolean navigated = homePage.selectCategoryAndSubcategory(categoria, subcategoria);
        if (!navigated) {
            Assert.fail("Categoría o subcategoría inexistente: " + categoria + " - " + subcategoria);
        }
    }

    @Y("agrego {int} unidades del primer producto al carrito")
    public void agrego_unidades_del_primer_producto_al_carrito(Integer cantidad) {
        productPage.addFirstProductToCart(cantidad);
    }

    @Entonces("valido en el popup la confirmación del producto agregado")
    public void valido_en_el_popup_la_confirmacion_del_producto_agregado() {
        boolean confirmation = productPage.validatePopupConfirmation();
        Assert.assertTrue("El popup de confirmación no se mostró correctamente", confirmation);
    }

    @Y("valido en el popup que el monto total sea calculado correctamente")
    public void valido_en_el_popup_que_el_monto_total_sea_calculado_correctamente() {
        boolean totalCorrect = productPage.validateTotalAmountInPopup();
        Assert.assertTrue("El monto total en el popup no fue calculado correctamente", totalCorrect);
    }

    @Cuando("finalizo la compra")
    public void finalizo_la_compra() {
        cartPage.completePurchase();
    }

    @Entonces("valido el titulo de la pagina del carrito")
    public void valido_el_titulo_de_la_pagina_del_carrito() {
        String title = cartPage.getCartPageTitle();
        Assert.assertEquals("Título de la página del carrito no es el esperado", "Your Cart", title);
    }

    @Y("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvo_a_validar_el_calculo_de_precios_en_el_carrito() {
        boolean priceCalculationCorrect = cartPage.validatePriceCalculation();
        Assert.assertTrue("El cálculo de precios en el carrito no es correcto", priceCalculationCorrect);
    }

    @Entonces("no debo acceder a la pantalla principal")
    public void no_debo_acceder_a_la_pantalla_principal() {
        boolean isLoggedIn = homePage.isUserLoggedIn();
        Assert.assertFalse("El usuario con credenciales inválidas accedió a la pantalla principal", isLoggedIn);
    }

    @Entonces("la automatización se detiene y se registra el error de categoría inexistente")
    public void la_automatizacion_se_detiene_y_se_registra_el_error_de_categoria_inexistente() {
        //
    }
}