package eu.senla.shabalin.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import eu.senla.shabalin.enums.SortBy;
import eu.senla.shabalin.enums.Column;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class ComputerDatabasePage {
    private String startPageUrl = "https://computer-database.gatling.io/computers";
    private String headerSortUp = "headerSortUp";
    private String headerSortDown = "headerSortDown";
    private SelenideElement headComputerName = $("th.col-name");
    private SelenideElement headComputerCompany = $("th.col-company");
    private SelenideElement displayAllRowsCount = $("li.current");
    private ElementsCollection computerNameElements = $$("td>a");
    private ElementsCollection computerCompanyElements = $$("tr>td:nth-child(4)");
    private ElementsCollection rowCollection = $$("tbody>tr");

    public ElementsCollection getAllRowsFromPage() {
        return rowCollection;
    }

    public List<String> getAllComputerNameInString(Column column) {
        if(column == Column.NAME){
            return computerNameElements.texts();
        } else {
            return computerCompanyElements.texts();
        }
    }

    public void openFirstPage() {
        open(startPageUrl);
    }

    public void openPenultimatePage() {
        String[] text = displayAllRowsCount.getText().split(" ");
        int penultimatePage =(Integer.parseInt(text[text.length-1])/10)-1;
        open(url().replaceFirst( "p=[0-9]", "p="+penultimatePage));
    }

    public void sortAllBy(Column sortedColumn, SortBy sortBy) {
        String sorter;
        SelenideElement column;

        if(sortedColumn == Column.NAME) {
            column = headComputerName;
        } else {
            column = headComputerCompany;
        }
        if(sortBy == SortBy.ASC) {
            sorter = headerSortUp;
        } else {
            sorter = headerSortDown;
        }
        while(!column.getAttribute("class").contains(sorter)) {
            column.$("a").click();
        }
    }
}
