package com.otus.components;

import com.google.inject.Inject;
import com.otus.annotations.Component;
import com.otus.data.menu.CourseTypeData;
import com.otus.data.menu.ItemData;
import com.otus.diconfig.GuiceScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Component("//*[contains(@class, 'header2-menu_main')]")
public class HeaderMenuComponent extends AbsComponent<HeaderMenuComponent> {

  @Inject
  public HeaderMenuComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  private String menuItemLocator = baseLocator + "//*[contains(@class, 'header2-menu__item_dropdown')][.//*[contains(@class, 'header2-menu__item')][text()='%s']]";
  private String menuItemDropdownListLocator = menuItemLocator + "//*[@class='header2-menu__dropdown']";

  public HeaderMenuComponent moveToMenuItem(ItemData itemData) {
    WebElement menuItemElement = driver.findElement(By.xpath(String.format(menuItemLocator, itemData.getName())));
    actions.moveToElement(menuItemElement).build().perform();

    return this;
  }

  public HeaderMenuComponent clickToMenuItem(ItemData itemData) {
    WebElement menuItemElement = driver.findElement(By.xpath(String.format(menuItemLocator, itemData.getName())));
    actions.click(menuItemElement).build().perform();

    return this;
  }

  public void checkSubMenuListVisible(ItemData itemData) {
    assert standartWaiter.waitForElementVisible(
        driver.findElement(By.xpath(String.format(menuItemDropdownListLocator, itemData.getName())))
    ) : "Sub menu not visible";

    //    return this;
  }

  public void checkSubMenuListNotVisible(ItemData itemData) {
    assert standartWaiter.waitForElementNotVisible(
        driver.findElement(By.xpath(String.format(menuItemDropdownListLocator, itemData.getName())))
    ) : "Sub menu visible";

    //    return this;
  }

  public void clickCourseItem(CourseTypeData courseTypeData) {
    String baseCourseTypeLocator = menuItemDropdownListLocator + "/*[contains(@class, 'header2-menu__dropdown-link')][@title='%s']";
    WebElement baseCourceElement = driver.findElement(By.xpath(String.format(baseCourseTypeLocator, ItemData.Courses.getName(), courseTypeData.getName())));

    baseCourceElement.click();

    //    actions.moveToElement(baseCourceElement).build().perform();
    //    assert standartWaiter.waitForCondition(ExpectedConditions.attributeContains(baseCourceElement, "class", "header2-menu__dropdown-wrapper_open")) : "";
    //
    //    baseCourceElement.findElement(By.xpath(String.format(".//a[@title='%s']", courseData.getName()))).click();

    //    return new LessonsPage((GuiceScoped) driver);
  }

}
