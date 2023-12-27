package hexlet.code.schemas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class StringSchema extends BaseSchema {
    private boolean isRequired = false;
    private String substring = "";
    private boolean isValid = true;


    private int minLength = 0;
    private String data;



    public StringSchema required() {
        this.isRequired = true;
        return this;
    }

    public StringSchema minLength(int min) {
        this.minLength = min;
        return this;
    }

    public StringSchema contains(String string) {
        this.substring = string;
        return this;
    }

    private void validate() {

        isValid = true;

        if ((isRequired) && (data == null || data == "")) {
            isValid = false;
        }
        if ((data == null) && (!substring.equals(""))) {
            isValid = false;
        }

        if (!(data == null) && !data.contains(substring)) {
            isValid = false;
        }

        if (!(data == null) && (data.length() < minLength)) {
            isValid = false;
        }

    }
    @Override
    public boolean isValid(Object str) {

        if (!(str == null) && !(str.getClass() == String.class)) {
            return  false;
        }


        data = (String) str;

        validate();
        return isValid;
    }


}

/*
Схема StringSchema содержит такой набор методов:

required() — делает данные обязательными для заполнения. Иными словами добавляет в схему ограничение,
которое не позволяет использовать null или пустую строку в качестве значения
minLength() — добавляет в схему ограничение минимальной длины для строки.
 Строка должна быть равна или длиннее указанного числа
contains() — добавляет в схему ограничение по содержимому строки.
Строка должна содержать определённую подстроку
Ограничения и minLength и contains могут быть настроены при помощи передачи параметра в метод:
 */
