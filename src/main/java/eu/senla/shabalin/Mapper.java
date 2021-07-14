package eu.senla.shabalin;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import eu.senla.shabalin.entity.Computer;
import org.checkerframework.checker.units.qual.C;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Mapper {
    private Date dateParser(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        if(!dateString.equals("-")) {
            return format.parse(dateString);
        } else {
            return null;
        }
    }

    private Computer mapComputerElementToEntity(SelenideElement element) {
        ElementsCollection allRows = element.$$("td");
        try {
            return new Computer(
                    allRows.get(0).getText(),
                    dateParser(allRows.get(1).getText()),
                    dateParser(allRows.get(2).getText()),
                    allRows.get(3).getText());
        }
        catch (ParseException e) {
            System.err.println("Не удалось преобразовать элемент в объект");
        }
        return null;
    }



    public List<Computer> mapComputerElementListToEntityList(ElementsCollection elementsCollection) {
        List<Computer> computerList = new ArrayList<>();
        elementsCollection.forEach(a -> computerList.add(mapComputerElementToEntity(a)));
        return computerList;

    }
}
