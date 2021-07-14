package eu.senla.shabalin.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import eu.senla.shabalin.SortBy;
import eu.senla.shabalin.SortedColumn;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class ComputerDatabasePage {
    private String headerSortUp = "headerSortUp";
    private String headerSortDown = "headerSortDown";
    private SelenideElement headComputerName = $("th.col-name>a");
    private SelenideElement headComputerCompany = $("th.col-company");
    private SelenideElement displayAllRowsCount = $("li.current");
    private ElementsCollection computerNameElemenList = $$("td>a");
    private ElementsCollection rowCollection = $$("tbody>tr");

    public ElementsCollection getAllRowsFromPage() {
        return rowCollection;
    }

    public List<String> getAllComputerNameInString() {
        List<String> computerNameList = new ArrayList<>();
         computerNameElemenList.forEach(a -> computerNameList.add(a.getText()));
        return computerNameList;
    }

    public ElementsCollection getAllComputerNameInElement() {
        return computerNameElemenList;
    }

    public ComputerDatabasePage openFirstPage() {
        open("https://computer-database.gatling.io/computers");
        return this;
    }

    public ComputerDatabasePage openPageWithAllRows() {
        openFirstPage();
        String[] text = displayAllRowsCount.getText().split(" ");
        String url = "https://computer-database.gatling.io/computers?p=0&n="+text[text.length-1];
        open(url);
        return this;
    }

    public ComputerDatabasePage openPenultimatePage() {
        openFirstPage();
        String[] text = displayAllRowsCount.getText().split(" ");
        Integer penultimatePage =(Integer.valueOf(text[text.length-1])/10)-1;
        String url = "https://computer-database.gatling.io/computers?p="+penultimatePage+"&n=10";
        open(url);
        return this;
    }

    public void sortAllBy(SortedColumn sortedColumn, SortBy sortBy) {
        String sorter;
        SelenideElement column;

        if(sortedColumn == SortedColumn.NAME) {
            column = headComputerName;
        } else {
            column = headComputerCompany;
        }
        if(sortBy == SortBy.ASC) {
            sorter = headerSortUp;
        } else {
            sorter = headerSortDown;
        }
        System.out.println(column.getAttribute("class"));
        while(!column.getAttribute("class").contains(sorter)) {
            column.$("a").click();
        }
    }
}
