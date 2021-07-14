package eu.senla.shabalin.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import eu.senla.shabalin.enums.SortBy;
import eu.senla.shabalin.enums.Column;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class ComputerDatabasePage {
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
        List<String> computerNameList = new ArrayList<>();
        ElementsCollection element;
        if(column == Column.NAME){
            element = computerNameElements;
        } else {
            element = computerCompanyElements;
        }
        element.forEach(a -> computerNameList.add(a.getText()));
        return computerNameList;
    }

    public ElementsCollection getAllComputerNameInElement() {
        return computerNameElements;
    }

    public ComputerDatabasePage openFirstPage() {
        open("https://computer-database.gatling.io/computers");
        return this;
    }

    public ComputerDatabasePage openPageWithAllRows() {
        String[] text = displayAllRowsCount.getText().split(" ");
        String url = "https://computer-database.gatling.io/computers?p=0&n="+text[text.length-1];
        open(url);
        return this;
    }

    public ComputerDatabasePage openPenultimatePage() {
        String[] text = displayAllRowsCount.getText().split(" ");
        Integer penultimatePage =(Integer.valueOf(text[text.length-1])/10)-1;
        open(url().replaceFirst( "p=[0-9]", "p="+penultimatePage));
        return this;
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
