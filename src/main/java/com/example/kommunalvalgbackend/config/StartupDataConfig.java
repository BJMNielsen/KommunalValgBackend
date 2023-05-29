package com.example.kommunalvalg.config;


import com.example.kommunalvalg.model.Kandidat;
import com.example.kommunalvalg.model.Parti;
import com.example.kommunalvalg.repository.KandidatRepository;
import com.example.kommunalvalg.repository.PartiRepository;
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

        // PARTI SOCIALDEMOKRATERNE //
        Parti socialdemokratiet = new Parti();

        socialdemokratiet.setPartiNavn("socialdemokratiet");
        socialdemokratiet.setPartiSymbol('A');
        // Nu saver vi partiet, som får et id autogenereret i databasen. Når vi .save noget, så returner den objektet du saver.
        // Vi gemmer så vores socialdemokrati vi får returnet fra .save metoden nedenunder, nu med en auto generated id, i vores gamle socialdemokratiet variabel, som ikke indeholder et id.
        System.out.println(socialdemokratiet.getId());
        socialdemokratiet = partiRepository.save(socialdemokratiet);
        System.out.println(socialdemokratiet.getId());


        // KANDIDATER SOCIALDEMOKRATIET //
        Kandidat Marcel_Meijer = new Kandidat();
        Marcel_Meijer.setNavn("Marcel Meijer");
        Marcel_Meijer.setParti(socialdemokratiet);
        kandidatRepository.save(Marcel_Meijer);

        Kandidat Michael_Kristensen = new Kandidat();
        Michael_Kristensen.setNavn("Michael Kristensen");
        Michael_Kristensen.setParti(socialdemokratiet);
        kandidatRepository.save(Michael_Kristensen);

        Kandidat Helle_Hansen = new Kandidat();
        Helle_Hansen.setNavn("Helle Hansen");
        Helle_Hansen.setParti(socialdemokratiet);
        kandidatRepository.save(Helle_Hansen);

        Parti Det_Konservative_parti = new Parti();
        Det_Konservative_parti.setPartiNavn("Det konservative Folkeparti");
        partiRepository.save(Det_Konservative_parti);

        Kandidat Per_Urban_Olsen = new Kandidat();
        Per_Urban_Olsen.setNavn("Per Urban Olsen");
        Per_Urban_Olsen.setParti(Det_Konservative_parti);
        kandidatRepository.save(Per_Urban_Olsen);

        Kandidat Peter_Askjar = new Kandidat();
        Peter_Askjar.setNavn("Peter Askjar");
        Peter_Askjar.setParti(Det_Konservative_parti);
        kandidatRepository.save(Peter_Askjar);

        Kandidat Martin_Sorensen = new Kandidat();
        Martin_Sorensen.setNavn("Martin Sorensen");
        Martin_Sorensen.setParti(Det_Konservative_parti);
        kandidatRepository.save(Martin_Sorensen);

        // PARTI  SF //////
        Parti SF = new Parti();
        SF.setPartiNavn("SF");
        SF.setPartiSymbol('F');
        partiRepository.save(SF);

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
    }
}

