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

public class CardOrderHappyPath {

    WebDriver driver;

    @BeforeAll
    static void setupDriver() {
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
    public void happyPath() {

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иван Иванов");
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

}

