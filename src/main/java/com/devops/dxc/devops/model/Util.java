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
        if(((ahorro*0.1)/getUf()) > 150 ){
            return (int) (150*getUf());
            
        } else if((ahorro*0.1)<=35*getUf() && ahorro >=35*getUf()){
            return (int) 35*getUf();
            
        } else if( ahorro <= 35*getUf()){
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
        int retorno = 0;
        
        //Se modifica formula con monto de sueldo ingresarlo a matriz de impuesto segun web https://www.chileatiende.gob.cl/fichas/81027-retiros-del-10-de-los-fondos-de-afp
        
        if (sueldo < 1500000)
        	retorno = 0;
        
        if (sueldo >= 1500000 && sueldo < 1530000) 
            retorno = (int)(0.04*dxc);
        
        if (sueldo >= 1530000 && sueldo < 2550000)
            retorno = (int)(0.08*dxc);
        
        if (sueldo >= 2550000 && sueldo < 3570000)
            retorno = (int)(0.135*dxc);
        
        if (sueldo >= 3570000 && sueldo < 4590000)
            retorno = (int)(0.23*dxc);
        
        if (sueldo >= 4590000 && sueldo <6120000)
            retorno = (int)(0.304*dxc);

        if (sueldo >= 6120000 && sueldo < 15818000)
            retorno =  (int)(0.35*dxc);

        if (sueldo >= 15818000)
            retorno =  (int)(0.4*dxc);

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

            if (serieObject.get("valor") instanceof Double) {
                valorUF = (int)Math.round((Double)serieObject.get("valor"));
            }
            else{
                valorUF = ((Number)serieObject.get("valor")).intValue();
            }

             
        } catch (ParseException e) {

            e.printStackTrace();
        }

        System.out.println(valorUF);
        return valorUF;
    }
    
}
