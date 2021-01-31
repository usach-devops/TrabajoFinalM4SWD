package com.devops.dxc.devops.model;

import org.springframework.web.client.RestTemplate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.text.SimpleDateFormat;
import java.util.*;

public class Util {

    /**
     * Método para cacular el 10% del ahorro en la AFP.  Las reglas de negocio se pueden conocer en 
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     * 
     * @param ahorro
     * @param sueldo
     * @return
     */
    public static int getDxc(int ahorro){
        if(((ahorro)/getUf()) > 150 ){
            return (int) (150*getUf()) ;
        } else if((ahorro*0.1)<=1000000 && ahorro >=1000000){
            return (int) 1000000;
        } else if( ahorro <=1000000){
            return (int) ahorro;
        } else {
            return (int) (ahorro*0.1);
        }
    }

    public static int getSaldo(int ahorro, int dxc)
    {
        if (ahorro>= dxc)
            return (ahorro - dxc);
        
            return 0;
    }

    public static int getImpuesto(int sueldo, int dxc)
    {
        int SueldoAnual = sueldo * 12;
        int retorno = 0;
        
        if (SueldoAnual > 17864280 && SueldoAnual <=29773800)
            retorno = (int)(0.08*dxc);

        if (SueldoAnual > 29773800 && SueldoAnual <=41600000)
            retorno = (int)(0.135*dxc);

        if (SueldoAnual > 41600000 && SueldoAnual <=53500000)
            retorno = (int)(0.23*dxc);

        if (SueldoAnual > 53500000 && SueldoAnual <=71400000)
            retorno =  (int)(0.304*dxc);

        if (SueldoAnual > 71400000)
            retorno =  (int)(0.35*dxc);

        return retorno;

    }

    /**
     * Método que retorna el valor de la UF.  Este método debe ser refactorizado por una integración a un servicio
     * que retorne la UF en tiempo real.  Por ejemplo mindicador.cl
     * @return
     */
    public static int getUf(){

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(formatter.format(date));
        
        RestTemplate plantilla = new RestTemplate();
        String JSON_DATA = plantilla.getForObject("https://mindicador.cl/api/uf/"+formatter.format(date), String.class);

        JSONParser jsonParser = new JSONParser();

        Object obj;
        int valorUF=0;

        try {
            obj = jsonParser.parse(JSON_DATA);
            JSONObject miIndicadorObject = (JSONObject) obj;
            JSONArray serieObjectList = (JSONArray)miIndicadorObject.get("serie");
            JSONObject serieObject = (JSONObject)serieObjectList.get(0); // UF

             valorUF = (int)Math.round((Double)serieObject.get("valor"));
        } catch (ParseException e) {

            e.printStackTrace();
        }

        System.out.println(valorUF);
        return valorUF;
    }
    
}
