package com.example.kommunalvalgbackend.service;

import com.example.kommunalvalgbackend.exception.ResourceNotFoundException;
import com.example.kommunalvalgbackend.model.Kandidat;
import com.example.kommunalvalgbackend.model.Parti;
import com.example.kommunalvalgbackend.repository.KandidatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

class KandidatServiceTest {

    @Mock
    private KandidatRepository kandidatRepository;

    @InjectMocks
    private KandidatService kandidatService;

    private List<Kandidat> mockedKandidatList = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        Parti socialdemokratiet = new Parti(1, "SocialDemokratiet", 'A');
        Parti SF = new Parti(2, "SF", 'F');

        Kandidat lars = new Kandidat(1,"Lars larsen", socialdemokratiet);
        Kandidat marianne = new Kandidat(2,"Marianne pedersen", SF);
        Kandidat ursula = new Kandidat(3,"Ursula Akvarie", socialdemokratiet);
        Kandidat glenn = new Kandidat(4,"Glenn Hansen", SF);

        // Vi hardcoder vores "database" af kandidater bare som en liste her.
        // Vi bruger asList i stedet for add, da vi vil gøre det for hver test, og .add vil adde dem alle for hver test, og det vil vi ikke.
        mockedKandidatList = Arrays.asList(lars, marianne, ursula, glenn); /* In your example, Arrays.asList(lars, marianne, ursula, glenn) creates a List containing the elements lars, marianne, ursula, and glenn. The elements are provided as separate arguments to the method. Now, let's compare Arrays.asList with mockedKandidatList.add:
            Arrays.asList creates a fixed-size List backed by an array, meaning you cannot add or remove elements from the resulting list. The size of the list will be the same as the number of elements provided.
            mockedKandidatList.add is a method call on an existing List object (mockedKandidatList in your example). It appends the specified element to the end of the list, effectively increasing its size.
            So, using Arrays.asList is suitable when you want to create a fixed-size List from a known set of elements, especially when you don't intend to modify the list later. On the other hand, using add allows you to dynamically add elements to an existing list, increasing its size as needed.*/

        // Hver gang vi laver en test køre vi hele setUp. Den her linje nedenunder tillader os at "mocke" opførslen
        MockitoAnnotations.openMocks(this);
    }

    // Her mocker vi den metoder der hedder getKandidatById som ligger i Kandidat Repository
    private void mockingGetKandidatById(int id){
        // Vi gennemgår vores liste af Kandidater (som mocker vores database med kandidater) og finder den hvis id passer med det id vi har givet metoden.
        Kandidat foundKandidat = null;
        for (Kandidat kandidat : mockedKandidatList) {
            if (kandidat.getId() == id) {
                foundKandidat = kandidat;
            }
        }
        Optional<Kandidat> optionalKandidat = Optional.ofNullable(foundKandidat);
        // Når kandidatRepository.findById(id) bliver kaldt inde i vores KandidatService, så (.then) returner vi optionalKandidat.
        when(kandidatRepository.findById(id)).thenReturn(optionalKandidat);
    }

    // For at lave en test, højreklikker man på klassen man gerne vil teste (inde i klassen), og vælger de metoder man vil teste.
    // Den her test, tester den metode der hedder getKandidatById inde i KandidatService.
    @Test
    // Selve testen tester om man bliver givet den korrekte kandidat, når man kalder getKandidatById metoden, og om der bliver kastet en ResourceNotFoundException, hvis ikke den rigtige kandidat bliver fundet..
    void getKandidatById() {
        // Mock the behavior of the repository
        Kandidat expectedKandidat = mockedKandidatList.get(0);
        int correctKandidatId = expectedKandidat.getId();
        int incorrectKandidatId = 0;

        // den her metode er nu kaldt og ligger og lytter
        mockingGetKandidatById(correctKandidatId); // Den her metode gør at når kandidatService kalder på kandidatRepository.findById(correctKandidatId) med det specifikke id (correctKandidatId), så vil den metode (kandidatRepository.findById(correctKandidatId) bringe tilbage en optional der indeholder den ønskede kandidat med correctKandidatId.
        mockingGetKandidatById(incorrectKandidatId); // Dvs mockingGetKandidatById returner i sig selv ikke noget, den gør bare at kandidatRepository.findById(correctKandidatId) returner den rigtige kandidat i en optional til kandidatService.

        // Call the service method.
        // Vi asserter(antager) at når vi kalder vores service lag metode getKandidatById, med korrekt id, så thrower den ikke en exception. Vi kalder getKandidateById direkte fra servicelaget, vha. @InjectMocks øvers oppe, som gør vi kan få fat i servicelaget direkte.
        Assertions.assertDoesNotThrow(() -> kandidatService.getKandidatById(correctKandidatId));
        // Vi vil gerne gemme den kandidat vi finder, fordi vi så kan spørgs om den expected kandidat == resultingKandidat.
        Kandidat resultingKandidat = kandidatService.getKandidatById(correctKandidatId);

        // Verify the result
        Assertions.assertThrows(ResourceNotFoundException.class, () -> kandidatService.getKandidatById(incorrectKandidatId)); // minitest i sig selv der tester at der bliver kastet en ResourceNotFoundException hvis vi kalder getKandidatById med inkorrekt id nr.
        Assertions.assertEquals(expectedKandidat.getId(), resultingKandidat.getId()); // her tester vi om expected kandidat ID == resulting kandidat id. Den kandidat vi har fundet, er det den vi forventede?
        Assertions.assertEquals(expectedKandidat.getParti().getId(), resultingKandidat.getParti().getId());
    }

    @Test
    void getAllKandidaterByParti() {

    }
}