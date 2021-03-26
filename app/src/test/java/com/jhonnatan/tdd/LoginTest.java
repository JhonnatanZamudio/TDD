package com.jhonnatan.tdd;

import com.jhonnatan.tdd.data.LoginDataSource;
import com.jhonnatan.tdd.data.LoginRepository;
import com.jhonnatan.tdd.ui.login.LoginViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
}