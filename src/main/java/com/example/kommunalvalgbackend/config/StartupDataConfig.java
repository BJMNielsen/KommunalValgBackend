package com.example.kommunalvalgbackend.config;


import com.example.kommunalvalgbackend.model.Kandidat;
import com.example.kommunalvalgbackend.model.Parti;
import com.example.kommunalvalgbackend.repository.KandidatRepository;
import com.example.kommunalvalgbackend.repository.PartiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;


//@Configuration annotationen og ApplicationRunner klassen gør at run metoden køre når applikationen starter.
@Configuration
public class StartupDataConfig implements ApplicationRunner {

    // Indsæt autowired her for ønsket funktionalitet.
    @Autowired
    PartiRepository partiRepository;

    @Autowired
    KandidatRepository kandidatRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("SYSTEMET STARTER");


        // PARTI SOCIALDEMOKRATERNE //
        Parti Socialdemokratiet = new Parti();
        Socialdemokratiet.setPartiNavn("Socialdemokratiet");
        Socialdemokratiet.setPartiSymbol('A');
        // Nu saver vi partiet, som får et id autogenereret i databasen. Når vi .save noget, så returner den objektet du saver.
        // Vi gemmer så vores socialdemokrati vi får returnet fra .save metoden nedenunder, nu med en auto generated id, i vores gamle Socialdemokratiet variabel, som ikke indeholder et id.
        System.out.println(Socialdemokratiet.getId()); // Socialdemokratiet's id i java er nul
        Socialdemokratiet = partiRepository.save(Socialdemokratiet); // For at få et id til vores Socialdemokratiet variabel i java, så saver vi objektet så der kommer et autoincrementet id, og save metoden returner det objekt du saver, nu med et id også, og den overwriter vi så vores gamle java variabel der endnu ikke har et id, men det returnet Socialdemokratiet vi har savet, der nu indeholder et ID efter saven.
        System.out.println(Socialdemokratiet.getId()); // Socialdemokratiet's id i java er nu 1

        // Her er den anden måde hvor man manuelt gemmer id'et og sætter.
        // int socialdemokratietID = partiRepository.save(Socialdemokratiet).getId(); Både saver og laver .getID på den savede ting.
        // Socialdemokratiet.setId(socialdemokratietID);

        // KANDIDATER SOCIALDEMOKRATIET //
        Kandidat Marcel_Meijer = new Kandidat();
        Marcel_Meijer.setNavn("Marcel Meijer");
        Marcel_Meijer.setParti(Socialdemokratiet);
        kandidatRepository.save(Marcel_Meijer);

        Kandidat Michael_Kristensen = new Kandidat();
        Michael_Kristensen.setNavn("Michael Kristensen");
        Michael_Kristensen.setParti(Socialdemokratiet);
        kandidatRepository.save(Michael_Kristensen);

        Kandidat Helle_Hansen = new Kandidat();
        Helle_Hansen.setNavn("Helle Hansen");
        Helle_Hansen.setParti(Socialdemokratiet);
        kandidatRepository.save(Helle_Hansen);

        // PARTI DET KONSERVATIVE FOLKEPARTI //////

        Parti detKonservativeParti = new Parti();
        detKonservativeParti.setPartiNavn("Det konservative Folkeparti");
        detKonservativeParti.setPartiSymbol('C');
        detKonservativeParti = partiRepository.save(detKonservativeParti);

        // KANDIDATER DET KONSERVATIVE FOLKEPARTI //

        Kandidat Per_Urban_Olsen = new Kandidat();
        Per_Urban_Olsen.setNavn("Per Urban Olsen");
        Per_Urban_Olsen.setParti(detKonservativeParti);
        kandidatRepository.save(Per_Urban_Olsen);

        Kandidat Peter_Askjar = new Kandidat();
        Peter_Askjar.setNavn("Peter Askjar");
        Peter_Askjar.setParti(detKonservativeParti);
        kandidatRepository.save(Peter_Askjar);

        Kandidat Martin_Sorensen = new Kandidat();
        Martin_Sorensen.setNavn("Martin Sorensen");
        Martin_Sorensen.setParti(detKonservativeParti);
        kandidatRepository.save(Martin_Sorensen);

        // PARTI  SF //////
        Parti SF = new Parti();
        SF.setPartiNavn("SF");
        SF.setPartiSymbol('F');
        SF = partiRepository.save(SF);

        // KANDIDATER SF  //
        Kandidat Ulla_Holm = new Kandidat();
        Ulla_Holm.setNavn("Ulla Holm");
        Ulla_Holm.setParti(SF);

        Kandidat Kjeld_Bonkel = new Kandidat();
        Kjeld_Bonkel.setNavn("Kjeld Bonkel");
        Kjeld_Bonkel.setParti(SF);
        kandidatRepository.save(Kjeld_Bonkel);

        Kandidat Anne_Olsen = new Kandidat();
        Anne_Olsen.setNavn("Anne_olsen");
        Anne_Olsen.setParti(SF);
        kandidatRepository.save(Anne_Olsen);

        // BLANDEDE KANDIDATER TIL SORTERING ///
        Kandidat larsLarsen = new Kandidat();
        larsLarsen.setNavn("Lars Larsen");
        larsLarsen.setParti(Socialdemokratiet);
        kandidatRepository.save(larsLarsen);

        Kandidat mortenDiggeldauer = new Kandidat();
        mortenDiggeldauer.setNavn("Morten Diggeldauer");
        mortenDiggeldauer.setParti(SF);
        kandidatRepository.save(mortenDiggeldauer);

        Kandidat piaK = new Kandidat();
        piaK.setNavn("Pia Kjærsgaard");
        piaK.setParti(detKonservativeParti);
        kandidatRepository.save(piaK);
    }
}

