package eu.senla.shabalin.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import eu.senla.shabalin.enums.Column;
import eu.senla.shabalin.enums.SortBy;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class ComputerDatabasePage {
    private final String START_PAGE_URL = "https://computer-database.gatling.io/computers";
    private final String HEADER_SORT_UP = "headerSortUp";
    private final String HEADER_SORT_DOWN = "headerSortDown";
    private final SelenideElement HEADER_COMPUTER_NAME = $("th.col-name");
    private final SelenideElement HEAD_COMPUTER_COMPANY = $("th.col-company");
    private final SelenideElement DISPLAY_ALL_ROWS_COUNT = $("li.current");
    private final ElementsCollection COMPUTER_NAME_ELEMENTS = $$("td>a");
    private final ElementsCollection COMPUTER_COMPANY_ELEMENTS = $$("tr>td:nth-child(4)");
    private final ElementsCollection ROW_COLLECTION = $$("tbody>tr");

    public ElementsCollection getAllRowsFromPage() {
        return ROW_COLLECTION;
    }

    public List<String> getComputerColumnInString(Column column) {
        if (column.equals(Column.NAME)) {
            return COMPUTER_NAME_ELEMENTS.texts();
        } else {
            return COMPUTER_COMPANY_ELEMENTS.texts();
        }
    }

    public void openFirstPage() {
        open(START_PAGE_URL);
    }

    public void openPenultimatePage() {
        String[] text = DISPLAY_ALL_ROWS_COUNT.getText().split(" ");
        int penultimatePage = (Integer.parseInt(text[text.length - 1]) / 10) - 1;
        open(url().replaceFirst("p=[0-9]", "p=" + penultimatePage));
    }

    public void sortAllBy(Column sortedColumn, SortBy sortBy) {

        SelenideElement column = sortedColumn.equals(Column.NAME) ? HEADER_COMPUTER_NAME : HEAD_COMPUTER_COMPANY;
        String sorter = sortBy.equals(SortBy.ASC) ? HEADER_SORT_UP : HEADER_SORT_DOWN;

        while (!Objects.requireNonNull(column.getAttribute("class")).contains(sorter)) {
            column.$("a").click();
        }
    }
}
