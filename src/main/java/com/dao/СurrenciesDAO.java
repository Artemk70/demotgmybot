package com.dao;

import com.entity.currency.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class СurrenciesDAO {
    @JsonProperty("AUD")
    private Aud aud;
    @JsonProperty("AZN")
    private Azn azn;
    @JsonProperty("GBP")
    private Gbp gbp;
    @JsonProperty("AMD")
    private Amd amd;
    @JsonProperty("BYN")
    private Byn byn;
    @JsonProperty("BGN")
    private Bgn bgn;
    @JsonProperty("BRL")
    private Brl brl;
    @JsonProperty("HUF")
    private Huf huf;
    @JsonProperty("HKD")
    private Hkd hkd;
    @JsonProperty("DKK")
    private Dkk dkk;
    @JsonProperty("USD")
    private Usd usd;
    @JsonProperty("EUR")
    private Eur eur;
    @JsonProperty("INR")
    private Inr inr;
    @JsonProperty("KZT")
    private Kzt kzt;
    @JsonProperty("CAD")
    private Cad cad;
    @JsonProperty("KGS")
    private Kgs kgs;
    @JsonProperty("CNY")
    private Cny cny;
    @JsonProperty("MDL")
    private Mdl mdl;
    @JsonProperty("NOK")
    private Nok nok;
    @JsonProperty("PLN")
    private Pln pln;
    @JsonProperty("RON")
    private Ron ron;
    @JsonProperty("XDR")
    private Xdr xdr;
    @JsonProperty("SGD")
    private Sgd sgd;
    @JsonProperty("TJS")
    private Tjs tjs;
    @JsonProperty("TRY")
    private Try aTry;
    @JsonProperty("TMT")
    private Tmt tmt;
    @JsonProperty("UZS")
    private Uzs uzs;
    @JsonProperty("UAH")
    private Uah uah;
    @JsonProperty("CZK")
    private Czk czk;
    @JsonProperty("SEK")
    private Sek sek;
    @JsonProperty("CHF")
    private Chf chf;
    @JsonProperty("ZAR")
    private Zar zar;
    @JsonProperty("KRW")
    private Krw krw;
    @JsonProperty("JPY")
    private Jpy jpy;

    @Override
    public String toString() {
        return "" + usd + eur;
    }
}
