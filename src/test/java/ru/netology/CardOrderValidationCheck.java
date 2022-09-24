package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CardOrderValidationCheck {

    WebDriver driver;

    @BeforeAll
    static void driver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    public void close() {
        //выход
        driver.quit();
        //обнуляем браузер
        driver = null;
    }

    @Test
    public void notValidNameEN() {

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Ivan");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+71111111111");
        //чек-бокс согласие на обработку персональных данных
        //для нажатия используется метод клик
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        //нажать кнопку отправить
        //в данном случае тег button является уникальным, мы можем обратиться к нему через teg name
        driver.findElement(By.cssSelector(".button__text")).click();
        //проверяем что открылась страница с сообщением об успешной отправке
        //если тег className длинный, но уникальный можно искать по части имени
        //поиск этого элемента на другой странице, где отправка успешна
        driver.findElement(By.cssSelector(".input__sub")).getText();
        //создаем текстовую переменную для сравнения
        String text = driver.findElement(By.cssSelector(".input__sub")).getText();
        //выполняем сравнение
        //тест может упасть если сравниваемый текст написан по-разному, например спереди ли сзади есть пробелы
        //метод .trim() убирает пробелы спереди и сзади
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }
    @Test
    public void emptyFieldName() {

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+71111111111");
        //чек-бокс согласие на обработку персональных данных
        //для нажатия используется метод клик
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        //нажать кнопку отправить
        //в данном случае тег button является уникальным, мы можем обратиться к нему через teg name
        driver.findElement(By.cssSelector(".button__text")).click();
        //проверяем что открылась страница с сообщением об успешной отправке
        //если тег className длинный, но уникальный можно искать по части имени
        //поиск этого элемента на другой странице, где отправка успешна
        driver.findElement(By.cssSelector(".input__sub")).getText();
        //создаем текстовую переменную для сравнения
        String text = driver.findElement(By.cssSelector(".input__sub")).getText();
        //выполняем сравнение
        //тест может упасть если сравниваемый текст написан по-разному, например спереди ли сзади есть пробелы
        //метод .trim() убирает пробелы спереди и сзади
        assertEquals("Поле обязательно для заполнения", text.trim());
    }
    @Test
    public void doubleSurname() {

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иванов-Андреев Иван");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+71111111111");
        //чек-бокс согласие на обработку персональных данных
        //для нажатия используется метод клик
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        //нажать кнопку отправить
        //в данном случае тег button является уникальным, мы можем обратиться к нему через teg name
        driver.findElement(By.cssSelector(".button__text")).click();
        //проверяем что открылась страница с сообщением об успешной отправке
        //если тег className длинный, но уникальный можно искать по части имени
        //поиск этого элемента на другой странице, где отправка успешна
        driver.findElement(By.className("paragraph_theme_alfa-on-white")).getText();
        //создаем текстовую переменную для сравнения
        String text = driver.findElement(By.className("paragraph_theme_alfa-on-white")).getText();
        //выполняем сравнение
        //тест может упасть если сравниваемый текст написан по-разному, например спереди ли сзади есть пробелы
        //метод .trim() убирает пробелы спереди и сзади
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
    @Test
    public void notValidPhone12numbers() {

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иван");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+712345678910");
        //чек-бокс согласие на обработку персональных данных
        //для нажатия используется метод клик
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        //нажать кнопку отправить
        //в данном случае тег button является уникальным, мы можем обратиться к нему через teg name
        driver.findElement(By.cssSelector(".button__text")).click();
        //проверяем что открылась страница с сообщением об успешной отправке
        //если тег className длинный, но уникальный можно искать по части имени
        //поиск этого элемента на другой странице, где отправка успешна
        driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        //создаем текстовую переменную для сравнения
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        //выполняем сравнение
        //тест может упасть если сравниваемый текст написан по-разному, например спереди ли сзади есть пробелы
        //метод .trim() убирает пробелы спереди и сзади
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }
    @Test
    public void notValidPhone10numbers() {

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иван");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+7123456789");
        //чек-бокс согласие на обработку персональных данных
        //для нажатия используется метод клик
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        //нажать кнопку отправить
        //в данном случае тег button является уникальным, мы можем обратиться к нему через teg name
        driver.findElement(By.cssSelector(".button__text")).click();
        //проверяем что открылась страница с сообщением об успешной отправке
        //если тег className длинный, но уникальный можно искать по части имени
        //поиск этого элемента на другой странице, где отправка успешна
        driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        //создаем текстовую переменную для сравнения
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        //выполняем сравнение
        //тест может упасть если сравниваемый текст написан по-разному, например спереди ли сзади есть пробелы
        //метод .trim() убирает пробелы спереди и сзади
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }
    @Test
    public void notValidPhone1number() {

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иван");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+7");
        //чек-бокс согласие на обработку персональных данных
        //для нажатия используется метод клик
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        //нажать кнопку отправить
        //в данном случае тег button является уникальным, мы можем обратиться к нему через teg name
        driver.findElement(By.cssSelector(".button__text")).click();
        //проверяем что открылась страница с сообщением об успешной отправке
        //если тег className длинный, но уникальный можно искать по части имени
        //поиск этого элемента на другой странице, где отправка успешна
        driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        //создаем текстовую переменную для сравнения
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        //выполняем сравнение
        //тест может упасть если сравниваемый текст написан по-разному, например спереди ли сзади есть пробелы
        //метод .trim() убирает пробелы спереди и сзади
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }
    @Test
    public void notValidPhoneNoPlus() {

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иван");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("12345678910");
        //чек-бокс согласие на обработку персональных данных
        //для нажатия используется метод клик
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        //нажать кнопку отправить
        //в данном случае тег button является уникальным, мы можем обратиться к нему через teg name
        driver.findElement(By.cssSelector(".button__text")).click();
        //проверяем что открылась страница с сообщением об успешной отправке
        //если тег className длинный, но уникальный можно искать по части имени
        //поиск этого элемента на другой странице, где отправка успешна
        driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        //создаем текстовую переменную для сравнения
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        //выполняем сравнение
        //тест может упасть если сравниваемый текст написан по-разному, например спереди ли сзади есть пробелы
        //метод .trim() убирает пробелы спереди и сзади
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }
    @Test
    public void notValidPhoneEmptyField() {

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иван");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("");
        //чек-бокс согласие на обработку персональных данных
        //для нажатия используется метод клик
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        //нажать кнопку отправить
        //в данном случае тег button является уникальным, мы можем обратиться к нему через teg name
        driver.findElement(By.cssSelector(".button__text")).click();
        //проверяем что открылась страница с сообщением об успешной отправке
        //если тег className длинный, но уникальный можно искать по части имени
        //поиск этого элемента на другой странице, где отправка успешна
        driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        //создаем текстовую переменную для сравнения
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        //выполняем сравнение
        //тест может упасть если сравниваемый текст написан по-разному, например спереди ли сзади есть пробелы
        //метод .trim() убирает пробелы спереди и сзади
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

}
