package carnetDeVoyage.outils;
import carnetDeVoyage.outils.FabriqueDate;
import carnetDeVoyage.pages.CarnetDeVoyage;
import carnetDeVoyage.pages.Page;
import carnetDeVoyage.pages.PageDePresentation;
import carnetDeVoyage.pages.PageDuJour;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GestionDesFichierJson {


    public GestionDesFichierJson(){ }


    public void creerUnFichierJson(CarnetDeVoyage carnet,String file)
    {
        JSONArray array = new JSONArray();
        for(Page page : carnet)
        {
            JSONObject object = new JSONObject();
            object.put("page",pageEnObjetJson(page));
            array.add(object);
        }

        try(FileWriter fichier = new FileWriter(file+".json"))
        {
            fichier.write(array.toJSONString());
            fichier.flush();
        }catch (Exception e)
        {
            e.getMessage();
        }
    }

    public void creerUnFichierJson(CarnetDeVoyage carnet,File file)
    {
        JSONArray array = new JSONArray();
        for(Page page : carnet)
        {
            JSONObject object = new JSONObject();
            object.put("page",pageEnObjetJson(page));
            array.add(object);
        }

        try(FileWriter fichier = new FileWriter(file))
        {
            fichier.write(array.toJSONString());
            fichier.flush();
        }catch (Exception e)
        {
            e.getMessage();
        }
    }


    //
    private  JSONObject pageEnObjetJson(Page page)
    {
        JSONObject object = new JSONObject();

        object.put("titre",page.getTitre());
        object.put("numeroDePage",String.valueOf(page.getNumeroDePage()));

        if(page.estUnePageDePresentation())
        {
            object.put("type","PageDePresentation");
            object.put("auteur",page.getAuteur());
            SimpleDateFormat sdf = new SimpleDateFormat("dd MM YYYY");
            object.put("debutDuVoyage",sdf.format(page.getDebutDuVoyage()));
            object.put("finDuVoyage",sdf.format(page.getFinDuVoyage()));
            object.put("participant",page.getParticipant());
        }
        else if (page.estUnePageDuJour())
        {
            object.put("type","PageDuJour");
            object.put("textDuJour",page.getTextDuJour());
            object.put("dateDuJour",page.getDateDuJour());
            object.put("PathImage",page.getPathImage());
            object.put("localisation",page.getLocalisation());
            if (page.estLocaliser())
                object.put("estLocaliser","1");
            else
                object.put("estLocaliser","0");

            object.put("latitude",Double.toString(page.getLatitude()));
            object.put("longitude",Double.toString((Double) page.getLongitude()));
            if (page.getPathImageEnPlus().size()>0){
                StringBuilder stringBuilder = new StringBuilder();
                for (String s : page.getPathImageEnPlus())
                    stringBuilder.append(s+"\n");
                object.put("imageEnPlus",stringBuilder.toString());
            }
        }
        return object;
    }


    public void lireUnFichierJson(CarnetDeVoyage carnetDeVoyage,String path)
    {
        JSONParser jsonP = new JSONParser();

        try(FileReader file = new FileReader(path))
        {
            Object objet = jsonP.parse(file);
            JSONArray pagesList = (JSONArray) objet;
            carnetDeVoyage.supprimerTouteLesPages();
            //iterator over emp array
            pagesList.forEach(page -> carnetDeVoyage.ajouter(parseEmpObj ((JSONObject) page )));
            carnetDeVoyage.setNumPageActuel(1);

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        }


    // creer une page
    private Page parseEmpObj(JSONObject pages)
    {
        Page page ;
        JSONObject pageObj  = (JSONObject) pages.get("page");
        String titre = (String) pageObj.get("titre");
        String numeroDePage = String.valueOf(pageObj.get("numeroDePage"));
        String type = (String) pageObj.get("type");
        if(type.equals("PageDePresentation"))
        {
            String auteur = (String) pageObj.get("auteur");
            String debutDuVoyage = (String) pageObj.get("debutDuVoyage");
            String finDuVoyage = (String) pageObj.get("finDuVoyage");
            String participant = (String) pageObj.get("participant");
            page = new PageDePresentation();
            page.setAuteur(auteur);
            page.setParticipant(participant);
            String date[] = debutDuVoyage.split(" ");
            page.setDebutDuVoyage(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0]));
            date = finDuVoyage.split(" ");
            page.setFinDuVoyage(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0]));
        }
        else
        {
            String textDuJour = (String) pageObj.get("textDuJour");
            String dateDuJour = (String) pageObj.get("dateDuJour");
            String pathImage = (String) pageObj.get("PathImage");
            String estLocaliser = (String) pageObj.get("estLocaliser");
            String localisation = (String) pageObj.get("localisation");
            String latitude = (String) pageObj.get("latitude");
            String longitude =(String) pageObj.get("longitude");
            String imageEnPlus = (String) pageObj.get("imageEnPlus");
            String s[] = null;
            if (imageEnPlus != null)
                s = imageEnPlus.split("\\n");
            page = new PageDuJour();
            if(estLocaliser.equals("1"))
                page.setEstLocaliser(true);
            else
                page.setEstLocaliser(false);
            page.setTextDuJour(textDuJour);
            page.setDateDuJour(dateDuJour);
            page.setPathImage(pathImage);
            page.setLocalisation(localisation);
            page.setLatitude(Double.parseDouble(latitude) );
            page.setLongitude(Double.parseDouble(longitude));
            if (s != null)
                for (String str : s)
                    page.addImageEnPlus(str);
        }
        page.setTitre(titre);
        page.setNumeroDePage(Integer.parseInt(numeroDePage));
        return  page ;
    }


}
