import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.*;

public class TestPen {

    @BeforeMethod
    public void setUp() {

    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testWrite_isWorkFalse() {
        int inkContainerValue = 0;                  //ожидаем выполнения условия !isWork
        String word = "word";
        String response = "";                        //ожидаемый результат

        Pen pen = new Pen(inkContainerValue);
        assertEquals(pen.write(word), response);
    }

    @Test
    public void testWrite_sizeOfWordTrue_defaultSize() {
        String word = "word";
        int inkContainerValue = word.length() + 1;  //ожидаем пропуск условия !isWork и выполнения условия sizeOfWord<=inkContainerValue

        Pen pen = new Pen(inkContainerValue);       //создаем Pen со значением sizeOfWord по умолчанию
        assertEquals(pen.write(word), word);        //проверяем, что условие sizeOfWord<=inkContainerValue выполняется и метод возращает word
    }

    @Test
    public void testWrite_sizeOfWordFalse_defaultSize() {
        String word = "word";
        int inkContainerValue = 1;  //ожидаем пропуск условия !isWork и выполнения условия sizeOfWord<=inkContainerValue

        Pen pen = new Pen(inkContainerValue);       //создаем Pen со значением sizeOfWord по умолчанию
        assertEquals(pen.write(word), word.substring(0, inkContainerValue));        //проверяем, что условие sizeOfWord<=inkContainerValue НЕ выполняется и метод возращает первую букву word
    }

    @Test
    public void testGetColor() {
        String color = "RED";

        Pen pen = new Pen(1, 1.0, color);
        assertEquals(color , pen.getColor());
    }
    //***********Тестирование метода isWork()***************
    @Test
    public void testIsWork_containerPositive() {
        int inkContainerValue = 1;

        Pen pen = new Pen(inkContainerValue);
        assertTrue(pen.isWork());
    }

    @Test
    public void testIsWork_containerEmpty() {
        int inkContainerValue = 0;

        Pen pen = new Pen(inkContainerValue);
        assertFalse(pen.isWork());
    }
    //***********Тестирование метода isWork()***************


    @Test
    public void testDoSomethingElse() throws IOException {
        int inkContainerValue = 1000;
        double sizeLetter = 1.0;
        String color = "RED";

        Pen pen = new Pen (inkContainerValue, sizeLetter, color);

        File file = new File("temp.txt"); // создаем файл
        PrintStream ps = new PrintStream(file); // создаем поток вывода в файл
        PrintStream standardOut = System.out; // сохраняем стандартный поток вывода
        System.setOut(ps); // присваиваем файловый поток в качестве основного
        pen.doSomethingElse(); // вызываем метод
        Assert.assertEquals(Files.readAllLines(Paths.get(file.toURI())).get(0), color); // проверяем что результат в файле равен ожидаемому
        System.setOut(standardOut); // возвращаем метод в исходное состояние
    }
}