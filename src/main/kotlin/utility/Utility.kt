package utility

import com.beust.klaxon.Klaxon
import dto.MarketPrice
import java.net.URL
import java.util.regex.Pattern
import repo.AliasRepo

object RegexMatches {
    @JvmStatic
    fun processMessage(msg: String):String {
        getBitcoinStock();
        val pattern = "\\{(.*?)\\}"
        val r = Pattern.compile(pattern)
        val m = r.matcher(msg)
        var tempString =msg;
        while(m.find()){
            tempString = if(m.group().contains("coin")) {
                tempString.replace(m.group(),getBitcoinStock().toString())
            } else{
                tempString.replace(m.group(),getActualValue(m.group()))
            }
        }
        return tempString;
    }

    private fun getBitcoinStock():String {
        val result = URL("https://api.coindesk.com/v1/bpi/currentprice/CNY.json").readText();
        val jsonData = Klaxon().parse<MarketPrice>(result)
        if (jsonData != null) {
            return jsonData.bpi.USD.rate
        }
        return "sorry, coin price not available";
    }

    private fun getActualValue(key:String):String {
       val aliasRepo = AliasRepo()
       return aliasRepo.find("key",key).singleResult().value
    }
}

