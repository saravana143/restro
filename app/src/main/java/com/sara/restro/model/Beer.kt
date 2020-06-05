package com.sara.restro.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Beer (
    @SerializedName("id")
    @Expose
    val id: String? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("tagline")
    @Expose
    val tagline: String? = null,

    @SerializedName("first_brewed")
    @Expose
    val first_brewed: String? = null,

    @SerializedName("description")
    @Expose
    val description: String? = null,

    @SerializedName("image_url")
    @Expose
    val image_url: String? = null,

    @SerializedName("abv")
    @Expose
    val abv: Float = 0.0f,

    @SerializedName("ibu")
    @Expose
    val ibu: Float = 0.0f,

    @SerializedName("target_fg")
    @Expose
    val target_fg: Int = 0,

    @SerializedName("target_og")
    @Expose
    val target_og: Float = 0.0f,

    @SerializedName("ebc")
    @Expose
    val ebc: Int = 0,

    @SerializedName("srm")
    @Expose
    val srm: Float = 0.0f,

    @SerializedName("ph")
    @Expose
    val ph: Float = 0.0f,

    @SerializedName("attenuation_level")
    @Expose
    val attenuation_level: Float = 0.0f,

    @SerializedName("volume")
    @Expose
    val volume: Volume? = null,

    @SerializedName("boil_volume")
    @Expose
    val boil_volume: BoilVolume? = null,

    @SerializedName("method")
    @Expose
    val method: Method? = null,

    @SerializedName("ingredients")
    @Expose
    val ingredients: Ingredients? = null,

    @SerializedName("brewers_tips")
    @Expose
    val brewers_tips: String? = null,

    @SerializedName("contributed_by")
    @Expose
    val contributed_by: String? = null
)

class Volume {
}

class BoilVolume {
}

class Method {
}

class Ingredients {
}


