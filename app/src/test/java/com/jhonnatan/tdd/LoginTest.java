package com.jhonnatan.tdd;

import com.jhonnatan.tdd.data.LoginDataSource;
import com.jhonnatan.tdd.data.LoginRepository;
import com.jhonnatan.tdd.ui.login.LoginViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import android.util.Patterns;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoginTest {

    LoginDataSource loginDataSourceTest;
    LoginRepository loginRepositoryTest;
    LoginViewModel loginViewModelTest;

    @Before
    public void createLogHistory() {
        loginDataSourceTest = new LoginDataSource();
        loginRepositoryTest = new LoginRepository(loginDataSourceTest);
        loginViewModelTest = new LoginViewModel(loginRepositoryTest);
    }

    @Test
    public void campo_email_vacio() {
        String resultado = loginViewModelTest.validarCampos("","");
        assertEquals("", resultado);
    }

    @Test
    public void campo_email_menor_8_caracteres() {
        String resultado = loginViewModelTest.validarCampos("1234567","");
        assertEquals("El campo Email NO puede tener menos de 8 caracteres", resultado);
    }

    @Test
    public void campo_email_mas_8_caracteres_sin_formato() {
        String resultado = loginViewModelTest.validarCampos("123456789","");
        assertEquals("El Email es incorrecto", resultado);
    }

    @Test
    public void campo_email_mas_8_caracteres_con_formato_correcto() {
        String resultado = loginViewModelTest.validarCampos("tecsco2013@gmail.com","");
        assertEquals("El Email es correcto", resultado);
    }

}