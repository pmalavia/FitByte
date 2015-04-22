package com.example.fitbyte.fitbyte;

import android.os.Parcel;
import android.os.Parcelable;import java.lang.Override;import java.lang.String;

/**
 * Created by amirsaifi on 3/23/15.
 */
public class foodItem implements Parcelable {
    private String itemName;
    private String brandName;
    private int calories;
    private int carbs;
    private int fat;
    private int protein;
    private int servingSize;
    private String servingUnit;
    private int servingWeightinGrams;

    public foodItem()
    {

    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }

    public void setServingWeightinGrams(int servingWeightinGrams) {
        this.servingWeightinGrams = servingWeightinGrams;
    }

    public String getItemName() {
        return itemName;
    }

    public String getBrandName() {
        return brandName;
    }


    public int getCalories() {
        return calories;
    }

    public int getCarbs() {
        return carbs;
    }

    public int getFat() {
        return fat;
    }

    public int getProtein() {
        return protein;
    }

    public int getServingSize() {
        return servingSize;
    }

    public String getServingUnit() {
        return servingUnit;
    }

    public int getServingWeightinGrams() {
        return servingWeightinGrams;
    }

    public String toString()
    {
        return itemName + " cal: " + calories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private foodItem(Parcel in)
    {
        itemName = in.readString();
        brandName = in.readString();
        calories = in.readInt();
        carbs = in.readInt();
        fat = in.readInt();
        protein = in.readInt();
        servingSize = in.readInt();
        servingUnit = in.readString();
        servingWeightinGrams = in.readInt();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(itemName);
        dest.writeString(brandName);
        dest.writeInt(calories);
        dest.writeInt(carbs);
        dest.writeInt(fat);
        dest.writeInt(protein);
        dest.writeInt(servingSize);
        dest.writeString(servingUnit);
        dest.writeInt(servingWeightinGrams);
    }

    public static final Parcelable.Creator<foodItem> CREATOR = new Parcelable.Creator<foodItem>() {
        public foodItem createFromParcel(Parcel in) {
            return new foodItem(in);
        }

        public foodItem[] newArray(int size) {
            return new foodItem[size];
        }
    };
}
