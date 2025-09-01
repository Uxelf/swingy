package my.rpg.model.hero;

import my.rpg.model.artifact.*;

import java.io.*;
import java.util.*;

public class SaveHero {

    public static void saveHeroToFile(Hero hero){
        File folder = getSaveFolder();
        if (!folder.exists()){
            if (!folder.mkdir()){
                System.out.println("WARNING: There was an error while saving the character");
                return;
            }
        }

        String heroSaveFile = hero.getSaveFile();
        File saveFile;
        if (heroSaveFile == null){
            int i = 1;
            do{
                saveFile = new File(folder, "save_" + i + ".swy");
                i++;
            }while (saveFile.exists());
        }
        else{
            saveFile = new File(folder, heroSaveFile);
        }

        String heroString = heroToString(hero);

        try (FileWriter writer = new FileWriter(saveFile, true)) {
            writer.write(heroString);
            System.out.println("Hero saved at: " + saveFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    private static String heroToString(Hero hero){
        String data = "";

        data += "name " + hero.getName();
        data += "\nclass " + hero.getHeroClass();
        data += "\nlevel " + hero.getLevel();
        data += "\nexperience " + hero.getExperience();

        if (hero.getArtifact() == null){
            data += "\nattack " + hero.getAttack();
            data += "\ndefense " + hero.getDefense();
            data += "\nhp " + hero.getHp() + "/" + hero.getMaxHp();
        }
        else{
            data += "\nattack " + (hero.getAttack() - hero.getArtifact().getAttack());
            data += "\ndefense " + (hero.getDefense() - hero.getArtifact().getDefense());
            if (hero.getArtifact().getArtifactType() == ArtifactType.Helm){
                data += "\nhp " + (hero.getHp() - hero.getArtifact().getHp()) + "/" + (hero.getMaxHp() - hero.getArtifact().getHp());
            }
            else{
                data += "\nhp " + hero.getHp() + "/" + hero.getMaxHp();
            }
            data += "\nartifact " + hero.getArtifact().getArtifactType();
            switch (hero.getArtifact().getArtifactType()){
                case Helm -> data += "\nartifact_mod " + hero.getArtifact().getHp();
                case Armor -> data += "\nartifact_mod " + hero.getArtifact().getDefense();
                case Weapon -> data += "\nartifact_mod " + hero.getArtifact().getAttack();
            }
        }
        return data;
    }

    public static List<Hero> getSavedHeroes(){
        File saveFolder = getSaveFolder();
        File[] listOfFiles = saveFolder.listFiles(File::isFile);
        if (listOfFiles == null)
            return null;

        List<Hero> heroes = new ArrayList<>();
        for (File file : listOfFiles){
            String fileName = file.getName();
            String[] parts = fileName.split("\\.", 2);
            if (parts.length != 2 || !parts[1].equals("swy")){
                continue;
            }
            try{
                heroes.add(fileToHero(fileName));
            } catch (Exception e) {
                System.out.println("Error loading file " + fileName + ":\n" + e.getMessage());
            }
        }
        return heroes;
    }

    public static Hero fileToHero(String fileName) throws IOException {
        Map<String, String> data = fileToDataMap(fileName);

        requiredKey(data, "name");
        requiredKey(data, "class");
        requiredKey(data, "level");
        requiredKey(data, "experience");
        requiredKey(data, "attack");
        requiredKey(data, "defense");
        requiredKey(data, "hp");

        String[] hpData = data.get("hp").split("/", 2);
        if (hpData.length != 2)
            throw new IllegalArgumentException("Wrong data in save file");

        Hero.HeroBuilder heroBuilder = new Hero.HeroBuilder();
        heroBuilder.setSaveFile(fileName);
        heroBuilder.setName(data.get("name"));
        heroBuilder.setHeroClass(HeroClass.valueOf(data.get("class")));
        heroBuilder.setLevel(Integer.parseInt(data.get("level")));
        heroBuilder.setExperience(Integer.parseInt(data.get("experience")));
        heroBuilder.setAttack(Integer.parseInt(data.get("attack")));
        heroBuilder.setDefense(Integer.parseInt(data.get("defense")));
        heroBuilder.setMaxHp(Integer.parseInt(hpData[1]));
        heroBuilder.setHp(Integer.parseInt(hpData[0]));

        if (data.containsKey("artifact")){
            requiredKey(data, "artifact");
            requiredKey(data, "artifact_mod");

            Artifact artifact = null;
            switch (ArtifactType.valueOf(data.get("artifact"))){
                case Helm -> artifact = new Helm.ArtifactBuilder().setModifier(Integer.parseInt(data.get("artifact_mod"))).build();
                case Armor -> artifact = new Armor.ArtifactBuilder().setModifier(Integer.parseInt(data.get("artifact_mod"))).build();
                case Weapon -> artifact = new Weapon.ArtifactBuilder().setModifier(Integer.parseInt(data.get("artifact_mod"))).build();
            }

            heroBuilder.setArtifact(artifact);
        }

        return heroBuilder.build();
    }

    private static Map<String, String> fileToDataMap(String fileName) throws IOException {

        File saveFile ;
        saveFile = new File(getSaveFolder(), fileName);
        if (!saveFile.exists() || !saveFile.isFile()){
            throw new IllegalStateException("WARNING: File not found: " + fileName);
        }

        Map<String, String> data = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(" ", 2);
                if (parts.length == 2){
                    data.put(parts[0], parts[1]);
                }
            }
        }
        return data;
    }


    private static File getSaveFolder(){
        String home = System.getProperty("user.home");

        if (home == null || home.isBlank()) {
            home = System.getProperty("user.dir");
            System.out.println("WARNING: HOME not found, using the current directory: " + home);
        }

        return new File(home, "swingySaves");
    }

    private static void requiredKey(Map<String, String> data, String key){
        if (!data.containsKey(key) || data.get(key) == null){
            throw new IllegalArgumentException("Missing data [" + key + "]");
        }
    }

    public static void deleteSaveFile(String saveFileName){
        File saveFile;
        try{
            saveFile = new File(getSaveFolder(), saveFileName);
        } catch (NullPointerException _) {
            return;
        }

        if (saveFile.exists()){
            if (!saveFile.delete()){
                System.out.println("Error: unable to delete file " + saveFileName);
            }
        }
        else{
            System.out.println("Error: the file " + saveFileName + " no longer exists");
        }
    }
}
