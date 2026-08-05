package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SliderUtils {

    public static void setSliderValue(WebDriver driver, WebDriverWait wait, WebElement slider, int targetValue) {
        wait.until(ExpectedConditions.visibilityOf(slider));

        int min = Integer.parseInt(slider.getAttribute("aria-valuemin"));
        int max = Integer.parseInt(slider.getAttribute("aria-valuemax"));

        // Clamp target within allowed range
        if (targetValue < min) targetValue = min;
        if (targetValue > max) targetValue = max;

        slider.click(); // focus

        int currentValue = Integer.parseInt(slider.getAttribute("aria-valuenow"));
        int steps = targetValue - currentValue;
        Keys direction = steps >= 0 ? Keys.ARROW_RIGHT : Keys.ARROW_LEFT;

        for (int i = 0; i < Math.abs(steps); i++) {
            slider.sendKeys(direction);
        }

        int finalValue = Integer.parseInt(slider.getAttribute("aria-valuenow"));
        System.out.println("Slider [" + min + "-" + max + "] set to: " + finalValue + " (target was " + targetValue + ")");
    }
}