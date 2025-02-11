package ee.qrental.contract.core.service.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static com.lowagie.text.Font.*;
import static com.lowagie.text.PageSize.A4;
import static com.lowagie.text.Rectangle.NO_BORDER;
import static com.lowagie.text.alignment.HorizontalAlignment.*;
import static com.lowagie.text.alignment.HorizontalAlignment.RIGHT;
import static java.awt.Color.white;

@RequiredArgsConstructor
public class ContractToPdfConversionStrategyOld implements ContractToPdfConversionStrategy {

  @Override
  public boolean canApply(final ContractPdfModel contract) {
    // TODO add condition

    return false;
  }

  @SneakyThrows
  @Override
  public InputStream getPdfInputStream(final ContractPdfModel model) {
    final var contractPdfDoc = new Document(A4, 40f, 40f, 50f, 50f);
    final var contractPdfOutputStream = new ByteArrayOutputStream();
    final var writer = PdfWriter.getInstance(contractPdfDoc, contractPdfOutputStream);
    contractPdfDoc.open();
    contractPdfDoc.add(getHeaderTable(model));
    contractPdfDoc.add(new Paragraph("\n"));
    contractPdfDoc.add(getRenterTable(model));
    contractPdfDoc.add(getTenantTable(model));

    final var body1 = new Table(2);
    body1.setWidths(new float[] {1, 20});
    body1.setPadding(0f);
    body1.setSpacing(0f);
    body1.setWidth(100f);
    body1.setBorderColor(white);
    body1.setHorizontalAlignment(LEFT);
    body1.setBorder(NO_BORDER);
    body1.addCell(getChapterCell("I"));

    final var body1cell2 = new Cell(new Paragraph("Üldsätted", new Font(TIMES_ROMAN, 9, BOLD)));
    body1cell2.setBorder(NO_BORDER);
    body1cell2.setHorizontalAlignment(LEFT);
    body1.addCell(body1cell2);
    body1.addCell(getSubChapterCell("1.1"));
    body1.addCell(
        getQTextCell(
            "Lepingu põhitingimused kasutatud mõistete selgitused on toodud Lepingu üldtingimustes ja nende lisades, "
                + "mis on käesoleva lepingu lahutamata osa. Käesoleva lepingu allakirjutamisega kinnitab tagasivõtmatult Rentnik, "
                + "et ta on läbi lugenud, arusaanud, andnud ning nõustunud lepingu üldtingimustega. "));
    body1.addCell(getSubChapterCell("1.2"));
    body1.addCell(
        getQTextCell(
            "Kõik käesoleva lepingu eritingimused on pooltel vahel eraldi läbiräägitud ja kokkulepitud."
                + " Pooled tagasivõtmatult kinnitavad, et iga eritingimuses sätestatud omab imperatiivsed kohaldamist võrreldes üldtingimustes sätestatuga. "));
    body1.addCell(getSubChapterCell("1.3"));
    body1.addCell(
        getQTextCell(
            "Rendileandja ja Rentnik sõlmivad lepingu poolte majandustegevuse raames, mille alusel Rentnikul on õigus rentida tulu saamiseks,"
                + " ehk taksoteenuse osutamiseks vaba auto Rendileandja autopargist. Käesoleva lepingu kohaselt kohustub Rendileandja andma Rentnikule kasutamiseks lepingus "
                + "ja üldtingimustes toodud vaba sõidukit oma autopargist - rendieseme ehk Rendiauto. Rentnik on kohustatud maksma selle eest tasu (Renti) Rendileandjale kogu Rendiperioodi eest."));
    body1.addCell(getSubChapterCell("1.4"));
    body1.addCell(
        getQTextCell(
            "Rendileping sõlmitakse määramata tähtajaks, ja see kehtib poolte poolt ülesütlemise avalduse esitamiseni. "
                + "Leping pikeneb automaatselt perioodiks, mis on võrdne käesoleva lepingu punktis 3.2 nimetatud renditeenuse minimaalse kestusega. "));
    contractPdfDoc.add(body1);

    final var body2 = new Table(2);
    body2.setWidths(new float[] {1, 20});
    body2.setPadding(0f);
    body2.setSpacing(0f);
    body2.setWidth(100f);
    body2.setBorderColor(white);
    body2.setHorizontalAlignment(LEFT);
    body2.setBorder(NO_BORDER);
    body2.addCell(getChapterCell("II"));

    final var body2cell2 =
        new Cell(new Paragraph("Rendilepingu tingimused", new Font(TIMES_ROMAN, 9, BOLD)));
    body2cell2.setBorder(NO_BORDER);
    body2cell2.setHorizontalAlignment(LEFT);
    body2.addCell(body2cell2);
    body2.addCell(getSubChapterCell("2.1"));
    body2.addCell(
        getQTextCell(
            "Renditav ese (auto) on Rendileandja poolt Rentnikule vastavalt üleandmise vastuvõtmise aktile, "
                + "mis on käesoleva lepingu lahutamatu osa, antud Rendileandja autopargist vaba auto. "));
    body2.addCell(getSubChapterCell("2.2"));
    body2.addCell(
        getQTextCell(
            "Iga renditava auto hind lepitakse kokku eraldi ning sõltub autoomadustest. "
                + "Pooled fikseerivad rendihinda suurust eraldi üleandmise-vastuvõtmise aktis, mis on käesoleva lepingu lisa ja/või lisad. "));
    body2.addCell(getSubChapterCell("2.3"));
    body2.addCell(
        getQTextCell(
            "Rendiauto tagatis on 300 eurot. Rendileandjal on õigus tasaarvestada rendilepingu lõpetamisel Rentniku täitmata kohustused tagatise arvelt. "));
    body2.addCell(getSubChapterCell("2.4"));
    body2.addCell(
        getQTextCell(
            "Rentnik kohustub tasuma rendileandjale ettemaksu iga nädala rendi eest sularahas Rendileandja kontoris, "
                + "mis asub aadressil Lasnamäe 30a, Tallinn, või ülekandega Rendileandja pangakontole (või muule Rendileandja esindajaga maaratud pangakontole) "
                + "asjakohase selgitusega – ”autorent + auto number”, mitte hiljem, kui iga nädala teisipäeva kella 16:00’ni. "));
    body2.addCell(getSubChapterCell("2.5"));
    body2.addCell(
        getQTextCell("Rendileping on tähtajatu ning kehtib kuni lepingu ülesütlemiseni. "));
    contractPdfDoc.add(body2);

    final var body3 = new Table(2);
    body3.setWidths(new float[] {1, 20});
    body3.setPadding(0f);
    body3.setSpacing(0f);
    body3.setWidth(100f);
    body3.setBorderColor(white);
    body3.setHorizontalAlignment(LEFT);
    body3.setBorder(NO_BORDER);
    body3.addCell(getChapterCell("III"));

    final var body3cell2 =
        new Cell(new Paragraph(" Eritingimused ", new Font(TIMES_ROMAN, 9, BOLD)));
    body3cell2.setBorder(NO_BORDER);
    body3cell2.setHorizontalAlignment(LEFT);
    body3.addCell(body3cell2);

    body3.addCell(getSubChapterCell("3.1"));
    body3.addCell(
        getQTextCell(
            "Kütus ei sisaldu rendihinnas. Korralist tehnilist hooldust teostab Rendileandja."));
    body3.addCell(getSubChapterCell("3.2"));
    body3.addCell(
        getQTextCell(
            "Rendileandja poolt käesoleva koostöölepingu alusel osutatava renditeenuse  "
                + "minimaalne kestus on "
                + model.getDuration()
                + "  täis kalendrinädalat esimese rendiauto edastamise kuupäevast pärast käesoleva rendilepingu allkirjastamist. "
                + "Ülalnimetatud kestus hakatakse lugema tema esimese lepingujärgse rendiauto üleandmise-vastuvõtmise aktis märgitud kuupäevast. Renditeenuse kasutamise ennetähtaegsel "
                + "ja erakorralisel (käesoleva rendilepingu ja selle tüüptimgimuste alusel) lõpetamisel kohustub rentnik tasuma koige kasutamata rendinädalate eest vastavalt käesoleva "
                + "lepingu ja tema esimese lepingujärgse rendiauto üleandmise-vastuvõtmise akti tingimustele. "));
    body3.addCell(getSubChapterCell("3.3"));
    body3.addCell(
        getQTextCell(
            "Rentnik vastutab kahju eest vastavalt üldtingimustes ptk VI,  VII ja VIII sätestatud järgi."));
    body3.addCell(getSubChapterCell("3.4"));
    body3.addCell(
        getQTextCell(
            "Viivis iga tasumata jäetud päeva eest on 0,1% kalendripäevas tasumata summalt."));
    body3.addCell(getSubChapterCell("3.5"));
    body3.addCell(
        getQTextCell(
            "Võlalimiit on 240 eurot, mille ületamisel annab Rendileandja Rentnikule 14 päeva võlgade "
                + "kõrvaldamiseks siis edasi peatub Rendileandja renditeenuse osutamist ja loeb lepingu oluliselt rikutuks."));
    body3.addCell(getSubChapterCell("3.6"));
    body3.addCell(
        getQTextCell(
            "Pooled on leppinud kokku eraldi, et käesolev leping on sõlmitud Rentniku majandustegevuse raames ning käesolevale ei kohada VÕS sätestatud tarbija sätted."));
    body3.addCell(getSubChapterCell("3.6.1"));
    body3.addCell(
        getQTextCell(
            "Rentnik (esindaja ) füüsilise isikuna ("
                + getTextOrEmpty(model.getRenterCeoName())
                + " "
                + model.getRenterCeoTaxNumber()
                + ") avaldab ja kinnitab oma allkirjaga tagasivõtmatult,"
                + "et ta käendab käesolevas lepingus tekkitavad kohustused mis tekkivad majandustegevuse raames, kuivõrd olles Põhivõlgniku juhatuse liige ja Põhivõlgniku tegelik "
                + "kasusaav omanik ("
                + getTextOrEmpty(model.getRenterCeoName())
                + " "
                + model.getRenterCeoTaxNumber()
                + "), tagab Käendaja nimetatud lepingus tekkivad kohustused antava käendusega Pooled avaldavad, "
                + "et nad ei käsitle käesoleva võlatunnistuse antud käendust tarbijakäendusena võlaõigusseaduse tähenduses. Käendaja vastutab Rendileandja ees täies ulatuses solidaarselt,"
                + " tagades kõiki Rendileandja nõudeid Rentniku vastu, mis tekivad või võivad tekkida käesoleva lepingu alusel."));
    body3.addCell(getSubChapterCell("3.6.2"));
    body3.addCell(
        getQTextCell(
            "Pooled on leppinud kokku, et käendaja füüsilise isikuna maksimaalne vastutuse piir on kuus tuhat eurot."));
    body3.addCell(getSubChapterCell("3.6.3"));
    body3.addCell(
        getQTextCell(
            "Käendusega on tagatud nii põhi- kui kõrvalkohustuste täitmine, sealhulgas intresside, viiviste ja trahvide tasumise kohustus, "
                + "samuti Võlaõigusliku lepingu rikkumisest tuleneva kahju hüvitamise, Võlaõigusliku lepingu ülesütlemise või sellest taganemisega seotud kulude ja võla"
                + " sissenõudmise kulude tasumise kohustused. Käesolevale lepingule ei kohaldata VÕS § 143 sätestatut."));
    body3.addCell(getSubChapterCell("3.7"));
    body3.addCell(
        getQTextCell(
            "Auto renditeenuste lõpetamiseks kohustub rentnik teavitada oma soovist renditud auto tagastada "
                + model.getDuration1()
                + " päeva enne järgmist esmaspäeva."));
    body3.addCell(getSubChapterCell("3.8"));
    body3.addCell(
        getQTextCell(
            "Rentniku kohustused Rendileandja eest muutuvad sissenõutavaks  vastavalt lepingus sätestatud üldtingimustele."));
    contractPdfDoc.add(body3);

    final var body4 = new Table(2);
    body4.setWidths(new float[] {1, 20});
    body4.setPadding(0f);
    body4.setSpacing(0f);
    body4.setWidth(100f);
    body4.setBorderColor(white);
    body4.setHorizontalAlignment(LEFT);
    body4.setBorder(NO_BORDER);
    body4.addCell(getChapterCell("IV"));

    final var body4cell2 = new Cell(new Paragraph(" Lõppsätted ", new Font(TIMES_ROMAN, 9, BOLD)));
    body4cell2.setBorder(NO_BORDER);
    body4cell2.setHorizontalAlignment(LEFT);
    body4.addCell(body4cell2);
    body4.addCell(getSubChapterCell("4.1"));
    body4.addCell(
        getQTextCell(
            "Rendilepingust tulenevad vaidlused, milles Rentnik ja Rendileandja ei jõua kokkuleppele, "
                + "lahendatakse Harju Maakohtus vastavalt seadusele. Vaidluse läbivaatamisel kohtus rakendatakse käesoleva lepingu tingimusi."));
    body4.addCell(getSubChapterCell("4.2"));
    body4.addCell(
        getQTextCell(
            "Pooled lepivad kokku, et Lepingu tingimusteks ei loeta Poolte varasemaid tahteavaldusi, "
                + "tegusid ega kokkuleppeid, mis ei ole Lepingus ega üldtingimustes otseselt sätestatud."));
    body4.addCell(getSubChapterCell("4.3"));
    body4.addCell(
        getQTextCell(
            "Kõik käesoleva lepingu tingimustes tehtud muudatused loetakse kehtivaks ainult siis, "
                + "kui need on tehtud kirjalikult kehtiva lepingu lisana koos nende tingimustega nõustumise kinnitusega mõlema poole"
                + " allkirjade kujul käesoleval dokumendil. Kõik muud arutelud ja kokkulepped loetakse tühiseks."));
    contractPdfDoc.add(body4);

    final var rendileandja2 = new Table(1);
    rendileandja2.setPadding(0f);
    rendileandja2.setSpacing(0f);
    rendileandja2.setWidth(100f);
    rendileandja2.setBorderColor(white);
    rendileandja2.setHorizontalAlignment(LEFT);
    rendileandja2.setBorder(NO_BORDER);
    rendileandja2.setBorder(NO_BORDER);

    final var rendileandja2cell2 =
        new Cell(
            new Paragraph(
                model.getQFirmName()
                    + " rendilepingu sõiduauto taksoteenuse ja majandustegevuse kasutamiseks tüüptingimused",
                new Font(TIMES_ROMAN, 10, BOLD)));
    rendileandja2cell2.setBorder(NO_BORDER);
    rendileandja2cell2.setHorizontalAlignment(LEFT);
    rendileandja2.addCell(rendileandja2cell2);

    final var rendileandja2cell3 =
        new Cell(new Paragraph("Kehtivad alates 01.06.2024", new Font(TIMES_ROMAN, 7, BOLD)));
    rendileandja2cell3.setBorder(NO_BORDER);
    rendileandja2cell3.setHorizontalAlignment(LEFT);
    rendileandja2.addCell(rendileandja2cell3);

    contractPdfDoc.add(rendileandja2);

    // end add header
    final var body1a = new Table(2);
    body1a.setWidths(new float[] {1, 20});
    body1a.setPadding(0f);
    body1a.setSpacing(0f);
    body1a.setWidth(100f);
    body1a.setBorderColor(white);
    body1a.setHorizontalAlignment(LEFT);
    body1a.setBorder(NO_BORDER);
    body1a.addCell(getChapterCell("I"));

    final var body1acell2 = new Cell(new Paragraph("ÜLDSÄTTED", new Font(TIMES_ROMAN, 9, BOLD)));
    body1acell2.setBorder(NO_BORDER);
    body1acell2.setHorizontalAlignment(LEFT);
    body1a.addCell(body1acell2);
    body1a.addCell(getSubChapterCell("1.1"));
    body1a.addCell(
        getQTextCell(
            "Käesolevad juriidilise isiku Rendileandja renditeenuste kasutamise tingimused (tingimused), sätestavad: "));
    body1a.addCell(getSubChapterCell("1.1.1"));
    body1a.addCell(getQTextCell("sõiduki rentimist"));
    body1a.addCell(getSubChapterCell("1.1.2"));
    body1a.addCell(getQTextCell("sõiduki ja vara kasutamise tingimused ja nõuded"));
    body1a.addCell(getSubChapterCell("1.1.3"));
    body1a.addCell(getQTextCell("Rentniku vastutuse tingimused ja piirid"));
    body1a.addCell(getSubChapterCell("1.1.4"));
    body1a.addCell(getQTextCell("maksetingimused"));
    body1a.addCell(getSubChapterCell("1.1.5"));
    body1a.addCell(getQTextCell("mistahes muud suhted seoses teenuste kasutamisega."));
    body1a.addCell(getSubChapterCell("1.2"));
    body1a.addCell(
        getQTextCell(
            "Alates vastavalt VÕS § 339 rendilepingu sätestatule sõlmivad Rentnik ja Rendileandja lepingulise õigussuhte,"
                + "mida reguleerivad käesolevad tingimused (sealhulgas selle lisad), hinnakiri, teenuse hinnad ja muud sõiduki rentimise eritingimused (leping)."));
    body1a.addCell(getSubChapterCell("1.3"));
    body1a.addCell(
        getQTextCell(
            "Enne sõiduki renti võtmist peab Rentnik tutvuma hinnakirja ja muude renditingimustega. "
                + "Rendilepingu sõlmimisel loetakse, et Rentnik on teenuse hindadest, hinnakirjast ja muudest rentimistingimustest teadlik ja on nendega nõustunud."));
    body1a.addCell(getSubChapterCell("1.4"));
    body1a.addCell(
        getQTextCell(
            "Kui tingimuste sätestatud allikates esineb vastuolusid või lahknevusi, tõlgendatakse ja kohaldatakse lepingut järgmise prioriteetsuse alusel:"));
    body1a.addCell(getSubChapterCell("1.4.1"));
    body1a.addCell(
        getQTextCell(
            "rendilepingust toodud eritingimused, teenuse hinnad ja muud konkreetse sõiduki renditingimused;"));
    body1a.addCell(getSubChapterCell("1.4.2"));
    body1a.addCell(getQTextCell("käesolevad tingimused;"));
    contractPdfDoc.add(body1a);

    final var body2a = new Table(2);
    body2a.setWidths(new float[] {1, 20});
    body2a.setPadding(0f);
    body2a.setSpacing(0f);
    body2a.setWidth(100f);
    body2a.setBorderColor(white);
    body2a.setHorizontalAlignment(LEFT);
    body2a.setBorder(NO_BORDER);
    body2a.addCell(getChapterCell("II"));

    final var body2acell2 = new Cell(new Paragraph("MÕISTED", new Font(TIMES_ROMAN, 9, BOLD)));
    body2acell2.setBorder(NO_BORDER);
    body2acell2.setHorizontalAlignment(LEFT);
    body2a.addCell(body2acell2);
    body2a.addCell(getSubChapterCell("2.1"));
    body2a.addCell(getQTextCell("Rendileandja – " + model.getQFirmName()));
    body2a.addCell(getSubChapterCell("2.2"));
    body2a.addCell(
        getQTextCell(
            "Hinnakiri - lepingus ja/või eritingimustes ja/või auto üleandmise aktis fikseeritud rendi hind, trahvid, muud tasud ja lõivud. Käesolevate tingimustega nõustudes nõustub Rentnik samal ajal ka käesolevate tingimuste lahutamatuks osaks oleva hinnakirjaga."));
    body2a.addCell(getSubChapterCell("2.3"));
    body2a.addCell(
        getQTextCell(
            "Liikluseeskirjad - asjaomases riigis kehtivad liikluseeskirjad ja nendega seotud õigusaktide sätted."));
    body2a.addCell(getSubChapterCell("2.4"));
    body2a.addCell(
        getQTextCell(
            "Rentnik - Rendileandja klient (füüsiline ja/või juriidiline isik), kes nõustub käesolevate tingimustega ja kasutab vastavalt lepingule rendiauto teenuseid. Rentnikul on õigus kasutada sõidukit ainult siis, kui ta on vähemalt 21 aastat vana ja omab mootorsõiduki juhistaaži vähemalt kaks aastat. "));
    body2a.addCell(getSubChapterCell("2.5"));
    body2a.addCell(
        getQTextCell(
            "Kasutusperiood - ajavahemik sõiduki üleandmise-vastuvõtmise akti alusel võtmise hetkest kuni sõiduki tagastamise vastuvõtmise akti allkirjastamiseni. Kasutusperioodist lepitakse eraldi kokku lepingu eritingimustes. Kasutusperiood on arvestatud kalendri täisnädala kaupa ja rendinädalaid peetakse ajaperioodi alates uue nädala Esmaspäeva kella 10:00’ni kuni järgmise nädala Esmaspäeva kella 10:00’ni."));
    body2a.addCell(getSubChapterCell("2.6"));
    body2a.addCell(
        getQTextCell(
            "Teenused - Rendileandja poolt Rentnikule osutatavad autorendi teenused vastavalt VÕS  § 399 sätestatule, mille käigus Rendileandja annab teisele isikule (rentnikule) kasutamiseks rendilepingu eseme ning võimaldab talle rendilepingu esemest korrapärase majandamise reeglite järgi saadava vilja. Rentnik on kohustatud maksma selle eest tasu (renti)."));
    body2a.addCell(getSubChapterCell("2.7"));
    body2a.addCell(
        getQTextCell(
            "Üleandmise-vastuvõtmise akt - kirjalik dokument, millega fikseeritakse rendiauto registreerimisnumbrit, üleandmise-vastuvõtmise kuupäeva, nädala rendihinda, auto seisundit (vigastusi ja puudusi) üleandmise ajal, ning Rendileandja ja Rentniku nõusolekut nimetatud üleandmise-vastuvõtmise akti andmetega, mis kinnitatakse poolte allkirjadega."));
    body2a.addCell(getSubChapterCell("2.8"));
    body2a.addCell(
        getQTextCell(
            "Rendiauto kahjustuste fikseerimise akt on kirjalik dokument millega fikseeritakse rendiesemele tekitatud vigastused selle rentniku kasutamise aja jooksul. Akt sisaldab vigastuste kirjeldust ja määrab nende asukohti. Võimalusel lisatakse aktile kahjustuste fotosid."));
    body2a.addCell(getSubChapterCell("2.9"));
    body2a.addCell(
        getQTextCell(
            "Rendiauto (sõiduk) - vaba auto Rendileandja autopargist. Rendilepingu kohaselt kohustub Rendileandja andma Rentnikule kasutamiseks rendilepingu ning käesoleva tingimuste sätestatu alusel vaba sõidukit oma autopargist - rendilepingu eseme ehk Rendiauto. Rentnik on kohustatud maksma selle eest tasu (Renti) Rendileandjale kogu Rendiperioodi eest."));
    body2a.addCell(getSubChapterCell("2.10"));
    body2a.addCell(
        getQTextCell(
            "Rent - tasu rendiauto kasutamise perioodi eest vastavalt lepingus, aktis ja tüüptingimustes toodule."));
    body2a.addCell(getSubChapterCell("2.11"));
    body2a.addCell(
        getQTextCell(
            "Rendi arvutamise põhimõtted - lepinguline nädala renditasu suurus üleandmise-vastuvõtmise hetkeks sõltub üleantava auto omadustest ja määratakse eraldi iga auto üleandmise-vastuvõtmise aktis. Üleandmise-vastuvõtmise aktis määratud renditasu on aktuaalne vaid täisrendinädala eest tasumise puhul. Täpsemalt sätestatud p.12.3."));
    body2a.addCell(getSubChapterCell("2.12"));
    body2a.addCell(
        getQTextCell(
            "Leping - autorendi teenuste osutamise leping. Rentniku ja Rendiandja vahel sõlmitud teenuste osutamise leping loetakse sõlmituks alates allakirjutamise hetkest. Lepingut reguleerivad sätted, nagu on sätestatud käesolevate tingimuste p. 1.2. "));
    body2a.addCell(getSubChapterCell("2.13"));
    body2a.addCell(getQTextCell("Käendus - erikokkuleppe sõlmitud lepingus eritingimusena."));
    body2a.addCell(getSubChapterCell("2.14"));
    body2a.addCell(
        getQTextCell(
            "Võlalimiit –  võla summa mille ületamisel loetakse leping oluliselt rikutuks."));
    body2a.addCell(getSubChapterCell("2.15"));
    body2a.addCell(
        getQTextCell(
            "Muude käesolevates tingimustes kasutatud mõistete tähendus vastab tingimuste p. 1.2 nimetatud allikates sätestatud tähendusele."));
    contractPdfDoc.add(body2a);

    final var body3a = new Table(2);
    body3a.setWidths(new float[] {1, 20});
    body3a.setPadding(0f);
    body3a.setSpacing(0f);
    body3a.setWidth(100f);
    body3a.setBorderColor(white);
    body3a.setHorizontalAlignment(LEFT);
    body3a.setBorder(NO_BORDER);
    body3a.addCell(getChapterCell("III"));

    final var body3acell2 =
        new Cell(new Paragraph("SÕIDUKI KASUTUSTINGIMUSED", new Font(TIMES_ROMAN, 9, BOLD)));
    body3acell2.setBorder(NO_BORDER);
    body3acell2.setHorizontalAlignment(LEFT);
    body3a.addCell(body3acell2);
    body3a.addCell(getSubChapterCell("3.1"));
    body3a.addCell(
        getQTextCell(
            "Rendileandja kohustub tagama, et rendiauto on heas sõidukorras ja selle vahetuks otstarbeks kasutamiseks ja käitamiseks sobilik, võttes arvesse sõiduki tavapärast kulumist."));
    body3a.addCell(getSubChapterCell("3.2"));
    body3a.addCell(
        getQTextCell(
            "Defektideks ei loeta rikkeid ja talitlushäireid, mis ei mõjuta praegu ega lähitulevikus liiklusohutust (nt: kriimustused seadmete sise- ja välispindadel, varuosad, multimeediaseadmete talitlushäireid, andurite rikked). Puudusi fikseeritakse üleandmise-vastuvõtmise aktis."));
    body3a.addCell(getSubChapterCell("3.3"));
    body3a.addCell(getQTextCell("Renditeenuse kasutamisel peab Rentnik muuhulgas:"));
    body3a.addCell(getSubChapterCell("3.3.1"));
    body3a.addCell(
        getQTextCell(
            "Rentnik on kohustatud vaatama sõiduki üle enne selle vastuvõtmist ja veenduma selle sobilikkuses ja korrasolekus, tegema vastavasisulise märke autol olemasolevatest kahjustustest üleandmise-vastuvõtmise aktil ja panna oma allkirja nende kinnitamiseks. Rentniku allkiri kinnitab tema nõusolekut ainult üleandmise-vastuvõtmise aktil märgitud kahjustuste olemasoluga."));
    body3a.addCell(getSubChapterCell("3.3.2"));
    body3a.addCell(
        getQTextCell(
            "täitma sõiduki käitamisnõudeid, sealhulgas mistahes käesolevates tingimustes nimetamata nõudeid, mis on selliste esemete kasutamisel tavapärased; "));
    body3a.addCell(getSubChapterCell("3.3.3"));
    body3a.addCell(
        getQTextCell(
            "sõitma tähelepanelikult, ettevaatlikult, viisakalt ja ohutult, austades teisi liiklejaid ja inimesi, võttes kõik vajalikud ettevaatusabinõud ja ohustamata teiste liiklejate, teiste inimeste või nende vara ja keskkonna ohutust;"));
    body3a.addCell(getSubChapterCell("3.3.4"));
    body3a.addCell(
        getQTextCell(
            "käituma piisavalt ettevaatlikult, mõistlikult, vastutustundlikult ja teadlikult;"));
    body3a.addCell(getSubChapterCell("3.3.5"));
    body3a.addCell(
        getQTextCell(
            "olema täiesti kaine (0,00 promilli) ja mitte vaimset seisundit mõjutavate ainete mõju all;"));
    body3a.addCell(getSubChapterCell("3.3.6"));
    body3a.addCell(
        getQTextCell(
            "mitte juhtima sõidukit haige või väsinuna, kui sõiduki juhtimine võib ohustada liiklusohutust või kui esineb mõni muu põhjus, miks Rentnik ei saa sõidukit vastavalt õigusaktides sätestatud nõuetele ohutult juhtida;"));
    body3a.addCell(getSubChapterCell("3.3.7"));
    body3a.addCell(
        getQTextCell(
            "Rentnikul ei ole õigust lubada teistel isikutel sõidukit juhtida, kontrollida või kasutada, sõidukit edasi rentida, käesolevates tingimustes sätestatud mistahes õigusi või kohustusi üle anda;"));
    body3a.addCell(getSubChapterCell("3.3.8"));
    body3a.addCell(
        getQTextCell(
            "Rentnikul ei ole õigust kopeerida, muuta või kustutada sõiduki süsteemis olevaid andmeid, omastada, hävitada või muul viisil kahjustada sõidukis olevaid sõiduki dokumente (nt tehnilist passi);"));
    body3a.addCell(getSubChapterCell("3.3.9"));
    body3a.addCell(
        getQTextCell(
            "Rentnikule on keelatud anda üle autoga seotud dokumendid ning poolte suhted reguleerivad dokumendid kolmandatele isikutele ilma teise poole nõusolekuta."));
    body3a.addCell(getSubChapterCell("3.3.10"));
    body3a.addCell(
        getQTextCell("Rentnikul ei ole õigust sõidukit lahti monteerida, remontida või muuta;"));
    body3a.addCell(getSubChapterCell("3.3.11"));
    body3a.addCell(
        getQTextCell(
            "Rentnikul  ei ole õigust vedada sõidukis plahvatusohtlikke, tuleohtlikke, mürgiseid aineid või inimelule või -tervisele kahjulikke aineid jms ega õigust kasutada sõidukis või sõiduki lähedal kütteseadmeid, lahtist tuld või muid võimalikke tuleallikaid;"));
    body3a.addCell(getSubChapterCell("3.3.12"));
    body3a.addCell(
        getQTextCell(
            "Rentnikul ei ole õigust kasutada sõidukit eesmärkidel, milleks see ei ole ette nähtud või kohandatud, nt kauba vedamiseks või kasutada sõidukit suurema koorma vedamiseks (raskete veoste vedamiseks jne), suurte loomade vedamiseks, samuti metsas, veekogudes ja muudel maastikel sõitmiseks, sõidukit üle koormata, koormat mitte nõuetekohaselt kinnitada ja paigutada;"));
    body3a.addCell(getSubChapterCell("3.3.13"));
    body3a.addCell(
        getQTextCell(
            "Rentnikul ei ole õigust kasutada sõidukit võidusõitudel, võistlustel ega muudel spordi või võidusõiduga seotud eesmärkidel;"));
    body3a.addCell(getSubChapterCell("3.3.14"));
    body3a.addCell(
        getQTextCell(
            "Rentnikul ei ole õigust kasutada sõidukit õppesõidukina ega kasutada sõidukit teiste sõidukite vedamiseks; "));
    body3a.addCell(getSubChapterCell("3.3.15"));
    body3a.addCell(getQTextCell("täitma liikluseeskirju;"));
    body3a.addCell(getSubChapterCell("3.3.16"));
    body3a.addCell(
        getQTextCell(
            "kaitsma sõidukit, kasutama sõidukit ja selles olevat vara hoolikalt, võtma kõik mõistlikud meetmed sõiduki turvalisuse tagamiseks (st lukustama sõiduki, sulgema aknad, lülitama tuled ja muusikaseadme välja jne);"));
    body3a.addCell(getSubChapterCell("3.3.17"));
    body3a.addCell(
        getQTextCell(
            "tagama, et A. sõidukis ei suitsetata ja B. väikseid lemmikloomi veetakse selleks ette nähtud spetsiaalses transpordikastis;"));
    body3a.addCell(getSubChapterCell("3.3.18"));
    body3a.addCell(
        getQTextCell(
            "enne sõidukiga sõitma asumist peab Rentnik veenduma, et sõidukil ei ole ilmseid rikkeid ja/või defekte ning nende olemasolu korral viivitamatult teavitama Rendileandja taasesitamist võimaldavas vormis;"));
    body3a.addCell(getSubChapterCell("3.3.19"));
    body3a.addCell(getQTextCell("täitma muid õigusaktides sätestatud nõudeid. "));
    body3a.addCell(getSubChapterCell("3.3.20"));
    body3a.addCell(
        getQTextCell(
            "Sõiduki võib kasutada ainult Eesti territooriumil. Rentnikul on lubatud kasutada sõidukit väljaspool Eesti territooriumi üksnes Rendileandja eelneval kirjalikul nõusolekul. Ettevõte otsustab nõusoleku andmise pärast seda, kui on hinnanud Rentnikul taotluse individuaalselt."));
    contractPdfDoc.add(body3a);

    final var body4a = new Table(2);
    body4a.setWidths(new float[] {1, 20});
    body4a.setPadding(0f);
    body4a.setSpacing(0f);
    body4a.setWidth(100f);
    body4a.setBorderColor(white);
    body4a.setHorizontalAlignment(LEFT);
    body4a.setBorder(NO_BORDER);
    body4a.addCell(getChapterCell("IV"));

    final var body4acell2 =
        new Cell(
            new Paragraph(
                " SÕIDUKI KÄTTESAAMINE JA TAGASTAMINE  ", new Font(TIMES_ROMAN, 9, BOLD)));
    body4acell2.setBorder(NO_BORDER);
    body4acell2.setHorizontalAlignment(LEFT);
    body4a.addCell(body4acell2);
    body4a.addCell(getSubChapterCell("4.1"));
    body4a.addCell(
        getQTextCell(
            "Sõiduki valjastamise koht on Lasnamäe 30a, Tallinn. Sõiduk antakse Rendileandja poolt üle Rentnikule kirjaliku poolte poolt allakirjutatud akti alusel."));
    body4a.addCell(getSubChapterCell("4.2"));
    body4a.addCell(
        getQTextCell(
            "Sõiduki kasutamise periood ei ole piiratud. Pooled võivad lepingu eritingimustes leppida kokku minimaalsest ja maksimaalsest renditeenuse kestusest."));
    body4a.addCell(getSubChapterCell("4.3"));
    body4a.addCell(
        getQTextCell(
            "Sõiduki tagastamiskoht on Lasnamäe 30a, Tallinn. Sõiduk tagastatakse Rendileandjale Rendileandja ja Rentniku poolt kirjaliku allkirjastatud akti alusel."));
    body4a.addCell(getSubChapterCell("4.4"));
    body4a.addCell(
        getQTextCell(
            "Rentnik on kohustatud kirjaliku üleandmise -vastuvõtmise akti alusel tagastama sõiduki lepingus määratud Rendiperioodi lõppemisel kokkulepitud kohas ja ettenähtud ajal. Sõidukit ei tohi maha jätta."));
    body4a.addCell(getSubChapterCell("4.5"));
    body4a.addCell(
        getQTextCell(
            "Rentnik vastutab täies ulatuses ka puuduste eest, mis on tekkinud sõidukil, mis on tagastatud Rendileandjale ilma kirjaliku üleandmise-vastuvõtmise aktita, kui ei ole tõendatud vastupidi ja/või algses üleandmise aktis märgitud."));
    body4a.addCell(getSubChapterCell("4.6"));
    body4a.addCell(
        getQTextCell(
            "Tagatise summa arvestatakse lähtudes iga rendiauto omadusest eraldi, lepitakse kokku lepingutingimustega ning märgitakse lepingus või üleandmise aktis. Tagatise summa määr on 300 eurot, mis muutub tasumiseks kohustuslikuks viivitamatult peale rendilepingu allkirjastamist. Tagatise summa tagastatakse rentnikule mitte varem kui kolm nädalat parast auto rendiandjale tagastamist."));
    body4a.addCell(getSubChapterCell("4.7"));
    body4a.addCell(
        getQTextCell(
            "Rentnik peab tagastama rendiauto seisukorras, mis ei ole kehvem kui seisukord, milles selle kätte saadi ja võttes arvesse selle kulumist. Sõiduki kulumine määratakse vastavalt Eesti Liisinguühingute Liidu poolt koostatud ja avaldatud „Sõidukite loomuliku ja ebaloomuliku kulumise määramise juhendile“, mis on avaldatud liidu kodulehel https://www.liisingliit.ee/regulatsioon/juhendid-ja-aktid (juhend loetakse tingimuste lahutamatuks osaks), ja riikliku tehnoülevaatuse eeskirjale."));
    body4a.addCell(getSubChapterCell("4.8"));
    body4a.addCell(getQTextCell("Kulumine ei hõlma:"));
    body4a.addCell(getSubChapterCell("4.8.1"));
    body4a.addCell(
        getQTextCell(
            "purunenud, deformeerunud või muul viisil mehaaniliselt või termiliselt kahjustatud osi, seadmeid ja mehhanisme;"));
    body4a.addCell(getSubChapterCell("4.8.2"));
    body4a.addCell(
        getQTextCell(
            "sõiduki keres olevaid mõlke, värvikihis olevaid pragusid ja nähtavaid kriimustusi;"));
    body4a.addCell(getSubChapterCell("4.8.3"));
    body4a.addCell(getQTextCell("värvikihi kulumist sõiduki tugeva pesemise/puhastamise tõttu;"));
    body4a.addCell(getSubChapterCell("4.8.4"));
    body4a.addCell(
        getQTextCell(
            "kehva kvaliteediga remonditöid ja/või remonditöödest tulenevaid defekte (vaatamata sellele, et Rentnikul ei ole õigust sõidukit ise ega kolmandate isikute kaudu remontida);"));
    body4a.addCell(getSubChapterCell("4.8.5"));
    body4a.addCell(getQTextCell("sõidukikere akendel olevaid pragusid;"));
    body4a.addCell(getSubChapterCell("4.8.6"));
    body4a.addCell(
        getQTextCell(
            "sõiduki valest kasutamisest ja/või puhastamisest tingitud kriimustusi sõidukikere akendel;"));
    body4a.addCell(getSubChapterCell("4.8.7"));
    body4a.addCell(
        getQTextCell(
            "salongi kahjustusi ja määrdumist, näiteks põlenud või määrdunud istmeid, katkiseid armatuurlaua osi või muid plastosi, pagasiruumi luuki, akende avamise käepidemeid jms;"));
    body4a.addCell(getSubChapterCell("4.8.8"));
    body4a.addCell(getQTextCell("sõidukikere geomeetria vigastusi."));
    contractPdfDoc.add(body4a);

    final var body5 = new Table(2);
    body5.setWidths(new float[] {1, 20});
    body5.setPadding(0f);
    body5.setSpacing(0f);
    body5.setWidth(100f);
    body5.setBorderColor(white);
    body5.setHorizontalAlignment(LEFT);
    body5.setBorder(NO_BORDER);
    body5.addCell(getChapterCell("V"));

    final var body5cell2 =
        new Cell(new Paragraph(" SÜNDMUSED KASUTUSEPERIOODIL ", new Font(TIMES_ROMAN, 9, BOLD)));
    body5cell2.setBorder(NO_BORDER);
    body5cell2.setHorizontalAlignment(LEFT);
    body5.addCell(body5cell2);
    body5.addCell(getSubChapterCell("5.1"));
    body5.addCell(
        getQTextCell(
            "Kui sõiduk läheb rikki, sõiduki armatuurlaual hakkab põlema mistahes hoiatus, kuulda on kahtlast "
                + "kummalist heli või sõidukit ei ole võimalik enam ohutult kasutada ja juhtida, peab Rentnik koheselt: \n"
                + "a) lõpetama sõiduki kasutamise;\n"
                + "b) teavitama Rendileandja sellest telefoni teel ja\n"
                + "c) täitma muid Rendileandja juhiseid.\n"));
    body5.addCell(getSubChapterCell("5.2"));
    body5.addCell(
        getQTextCell(
            "Liiklusõnnetuse korral või sõiduki, kolmandate isikute või nende vara vigastamisel, Rendileandja või selle vara mistahes muul viisil kahjustamisel peab Rentnik Rendileandjat sellest koheselt teavitama ja vajadusel teavitama asjakohaseid riigiasutusi või teenistusi (politsei, tuletõrje jne), täitma liiklusõnnetuse deklaratsiooni ja/või tegema muid vajalikke toiminguid, mida tuleb vastavalt kohaldatavatele õigusaktidele teha, samuti toiminguid, mida tuleb teha sõidukile, muule varale ja/või isikutele suurema kahju tekkimise vältimiseks või kahju vähendamiseks."));
    body5.addCell(getSubChapterCell("5.3"));
    body5.addCell(
        getQTextCell(
            "Liiklusõnnetuse korral, milles pole Rentnik süüdi, tuleb liiklusõnnetuses osalenud juhtidel täita nõuetekohaselt LE vorm nr.1 või kutsuda sündmuskohale politsei, et liiklusõnnetuse asjaolusid fikseerida. Selle LE vorm nr.1 või politsei poolt koostatud sündmuskoha skeemi koopia mitteesitamisel Rendileandjale, on Rentnik täies ulatuses vastutav Rendileandjale tekitatud kahjude eest."));
    body5.addCell(getSubChapterCell("5.4"));
    body5.addCell(
        getQTextCell(
            "Kahju tekkimisel Rendileandjale või kolmandatele isikutele või dokumentide või esemete kadumisel on Rentnik kohustatud esitama Rendileandjale kirjaliku seletuse juhtunu kohta hiljemalt 24 tunni möödumisel. Kui puudub muu võimalus kirjaliku seletuse andmiseks, võib seda esitada Rendileandjale erandkorras ka lepingus toodud e-posti aadressile, varustades seletuse digitaalallkirjaga."));
    contractPdfDoc.add(body5);

    final var body6 = new Table(2);
    body6.setWidths(new float[] {1, 20});
    body6.setPadding(0f);
    body6.setSpacing(0f);
    body6.setWidth(100f);
    body6.setBorderColor(white);
    body6.setHorizontalAlignment(LEFT);
    body6.setBorder(NO_BORDER);
    body6.addCell(getChapterCell("VI"));
    final var body6cell2 = new Cell(new Paragraph("VASTUTUS", new Font(TIMES_ROMAN, 9, BOLD)));
    body6cell2.setBorder(NO_BORDER);
    body6cell2.setHorizontalAlignment(LEFT);
    body6.addCell(body6cell2);
    body6.addCell(getSubChapterCell("6.1"));
    body6.addCell(getQTextCell("Hüvitamise üldsätted"));
    body6.addCell(getSubChapterCell("6.1.1"));
    body6.addCell(
        getQTextCell(
            "Rentnik kui suurema ohu allika valdaja vastutab kogu sõiduki kasutusperioodi jooksul täielikult tingimuste ja õigusaktide rikkumise eest ning Rendileandjale, sõidukile ja/või kolmandatele isikutele tekitatud kahju eest."));
    body6.addCell(getSubChapterCell("6.1.2"));
    body6.addCell(
        getQTextCell(
            "Rentnik vastutab koos Rentnikuga sõidukit kasutavate isikute (nt kaassõitjate) ohutuse, tervise ja elu eest, samuti enda vara või teiste isikute vara kahjustamise, hävimise või kaotsimineku eest, kui kohaldatavates õigusaktides ei ole sätestatud teisiti."));
    body6.addCell(getSubChapterCell("6.1.3"));
    body6.addCell(
        getQTextCell(
            "Mitte ükski käesolevate tingimuste säte ei piira Rendileandjal õigust nõuda võlakohustuste täitmist kolmandate isikute poolt (vastavalt lepinguvälisele vastutusele), kes põhjustasid oma tegevuse või tegevusetusega Rendileandjale kahju, kuid selline Rendileandja õigus ei piira mingil juhul Rentniku eelnevalt nimetatud vastutust."));
    body6.addCell(getSubChapterCell("6.1.4"));
    body6.addCell(
        getQTextCell(
            "Käesolevate tingimuste tähenduses on Rendileandja poolt kantud kahju(d) järgmine(sed) (sealhulgas, kuid mitteainult):\n"
                + "a.\tRendileandja mainele, firmaväärtusele ja heale nimele, kaubamärgile ja ärinimele, Rendileandja üldjuhtimise põhimõtetele, samuti Rendileandja sotsiaalsele kuvandile tekitatud kahju;\n"
                + "b.\tsõidukile (sealhulgas selle väärtuse vähenemine), selle Rendileandjale või teistele isikutele kuuluvatele osadele ja varale, sealhulgas sõiduki lisatarvikutele tekitatud kahju;\n"
                + "c.\tkõik sõiduki transportimise, turvalisuse, puhastamise, parkimise, remonditöödega seotud kulud (nii tegelikult kantud kui ka veel kandmata kulud, mille sõltumatu hindaja on kindlaks teinud ja hinnanud kui vajalikud sõiduki remondikulud);\n"
                + "d.\tkahju hindamise, kindlakstegemise, kahjukäsitluse ja asjaajamisega seotud kulud;\n"
                + "e.\tsõiduki müügi, võõrandamisega seotud kulud;\n"
                + "f.\tvõlgnevuse sissenõudmise kulud;\n"
                + "g.\tkaudne kahju (nt saamata jäänud tulu, sõiduki jõude seismise aeg);\n"
                + "h.\tkahju ärahoidmise või vähendamisega seotud kulud;\n"
                + "i.\tkindlustushüvitised, mis jäävad Rendileandjale välja maksmata Rentniku poolt tingimuste rikkumise tõttu.\n"));
    final var body6cell13 = new Cell(new Paragraph("6.1.5", new Font(TIMES_ROMAN, 9, NORMAL)));
    body6cell13.setBorder(NO_BORDER);
    body6cell13.setHorizontalAlignment(LEFT);
    body6.addCell(body6cell13);
    body6.addCell(
        getQTextCell(
            "Ilma et see piiraks käesolevate tingimuste mistahes sätteid, vastutab Rentnik täielikult sõiduki kahjustamise eest:\n"
                + "a.\tRentnik vastutab kogu kahju eest, mis on tema tegevuse ja või tegevusetuse tulemusena on tekitatud sõidukile (avarii, väline kahju, kriimustamised, deformeerimised jne. "
                + "Nimekiri ei ole lõplik).  Kahju hüvitamiseks pooled lähtuvad VÕS sätestatutest ja üleandmise-vastuvõtmise aktis märgitud andmetest.\n"
                + "b.\tkui sõiduk, selle lisatarvikud või nende mõni osa varastatakse või saab kahjustada, sest juht jättis aknad või katuseluugid lahti, katuse peale tõmbamata, uksed lukustamata jne;\n"
                + "c.\tkui sõiduk või selle mõni osa varastatakse või saab kahjustada isiku poolt, kes kasutas sõidukit koos Rentnikuga või Rentniku teadmisel ja tahtel.\n"));
    body6.addCell(getSubChapterCell("6.1.6"));
    body6.addCell(
        getQTextCell(
            "Juhul kui tingimusi rikub või muid ebaseaduslikke tegevusi või tegematajätmisi teeb ja/või kahju Rendileandjale ja/või teistele isikutele,"
                + " sealhulgas Rentnikule endale põhjustab kolmas isik, kellele Rentnik lubab, annab nõusoleku, annab üle või muul viisil võimaldab või teeb võimalikuks või mistahes muul viisil "
                + "loob oma aktiivse või passiivse tegevuse ja/või tegevusetusega otseselt või kaudselt, tahtlikult või hooletuse tõttu kolmandale isikule või kolmandate isikute rühmale võimaluse"
                + " pääseda sõidukisse, juhtida ja/või kasutada muul viisil sõidukit, selle lisatarvikuid ja/või  ei takista seda:\n"
                + "a.\t kannab Rentnik kõiki kolmandate isikute tegevusest või tegevusetusest tingitud tingimuste ja seaduste rikkumisest tulenevaid riske, vastutust ja kahju ja/või "
                + "vastutab Rendileandjale ja/või kolmandatele isikutele põhjustatud kahju eest;\n"
                + "b.\tkohaldatakse Rentniku suhtes tingimustes sätestatud trahve, kahjuhüvitisi ja muud vastutust ning nendest tulenevaid tagajärgi, lugedes sellised tegevused ja "
                + "rikkumised Rentnik enda poolt toime panduks ja kahju Rentnik enda poolt põhjustatuks;\n"
                + "c.\tLepingus nimetatud trahvid, parkimistrahvid, kiiruseületamise trahvid ja muud järelevalve organite poolt määratud trahvid Rentnik kohustatud tasuma 7 (seitsme) "
                + "päeva jooksul pärast Rentnikule trahvi teatavaks tegemisest ning kättetoimetamisest. Kättetoimetamine võib olla elektronile, sms, e-kiri, füüsiliselt kätte andmine jne.\n"));
    contractPdfDoc.add(body6);

    final var body7 = new Table(2);
    body7.setWidths(new float[] {1, 20});
    body7.setPadding(0f);
    body7.setSpacing(0f);
    body7.setWidth(100f);
    body7.setBorderColor(white);
    body7.setHorizontalAlignment(LEFT);
    body7.setBorder(NO_BORDER);
    body7.addCell(getChapterCell("VII"));

    final var body7cell2 = new Cell(new Paragraph(" TRAHVID ", new Font(TIMES_ROMAN, 9, BOLD)));
    body7cell2.setBorder(NO_BORDER);
    body7cell2.setHorizontalAlignment(LEFT);
    body7.addCell(body7cell2);
    body7.addCell(getSubChapterCell("7.1"));
    body7.addCell(
        getQTextCell(
            "Tingimustes käsitletakse trahve ja nende määramise tingimusi, põhimõtteid ja korda, kuid konkreetsed trahviliigid ja nende konkreetsed summad on toodud lepingus ja või käesolevates tingimustes."));
    body7.addCell(getSubChapterCell("7.2"));
    body7.addCell(
        getQTextCell(
            "Igal juhul Rentnik peab enne rendilepingu sõlmimist tutvuma kohaldatava ja hetkel kehtiva trahvinimekirjaga."));
    body7.addCell(getSubChapterCell("7.3"));
    body7.addCell(
        getQTextCell(
            "Tingimustes käsitletavad ja lepingus loetletud trahvid loetakse Rendileandja poolt määravaks leppetrahviks."));
    body7.addCell(getSubChapterCell("7.4"));
    body7.addCell(
        getQTextCell(
            "Kõik tingimustes ja lepingus nimetatud trahvid, välja arvatud tingimustes konkreetselt sätestatud erandid, on kõikehõlmavad, st need sisaldavad Rendileandjale tekitatud kahju. Lisaks sellele peab Rentnik trahvi maksmisel hüvitama Rendileandjale kõik täiendavad summad või täiendavad kahjuliigid, mida tasutud trahv ei kata. Trahvi tasumine ei vabasta Rentnikku kohustusest hüvitada kõik muud Rendileandja poolt kantud kahjud, mida tasutud trahv ei kata. Lisaks ei vabasta trahvi määramine Rentnikku kohustusest täita muid käesolevates tingimustes ja/või õigusaktides sätestatud kohustusi ulatuses, milles Rentniku poolt tasutud trahv ei kata või asenda selliseid kohustusi vastavalt nende olemusele või sisule. Kõik erinevad Rentniku individuaalsetest tegevustest põhjustatud kahjuliigid määratletakse ja neid hinnatakse eraldi, isegi kui need on põhjustatud samal ajal. Rentniku sellistest individuaalsetest tegevustest põhjustatud individuaalsete/erinevate kahjuliikide hüvitamine (trahvi tasumine ja/või kahju hüvitamine) ei ole vastastiku kaasav ja seda kohaldatakse iga kahjuliigi ja seda kahjuliiki põhjustanud Rentniku vastavate tegevuste suhtes eraldi."));
    body7.addCell(getSubChapterCell("7.5"));
    body7.addCell(
        getQTextCell(
            "Rentnik peab maksma Rendileandjale hinnakirjas toodud summas trahvi muu hulgas järgmistel juhtudel (allpool toodud juhtude loetelu on toodud üksnes illustreerimiseks ja trahvide ammendav loetelu on toodud hinnakirjas):"));
    body7.addCell(getSubChapterCell("7.5.1"));
    body7.addCell(
        getQTextCell(
            "sõiduki, selle osade, lisatarvikute, lisaseadmete (sealhulgas sõiduki võtme) või seadmete kahjustamise või kaotsimineku korral;"));
    body7.addCell(getSubChapterCell("7.5.2"));
    body7.addCell(getQTextCell("sõidukis suitsetamise korral;"));
    body7.addCell(getSubChapterCell("7.5.3"));
    body7.addCell(getQTextCell("ohtliku, hoolimatu või hooletu sõitmise korral;"));
    body7.addCell(getSubChapterCell("7.5.4"));
    body7.addCell(
        getQTextCell(
            "määrdunud, musta sõiduki korral, kui sõiduk on määrdunum kui sõidukite tavapärase kasutamise korral (näiteks maastikul, metsas, veekogudes, madalsoodes, mägedel, ainult eritranspordi või spetsiaalselt ettevalmistatud sõidukitega ligipääsetavates kohtades sõitmisel või liikluseeskirjade rikkumisel);"));
    body7.addCell(getSubChapterCell("7.5.5"));
    body7.addCell(
        getQTextCell(
            "alkoholijoobes (üle 0,00 promilli) või narkootiliste ainete ja muude vaimset seisundit mõjutavate ainete mõju all sõitmise korral (või kui Rentnik tarvitas alkoholi või muid joovastavaid aineid pärast liiklusõnnetust enne liiklusõnnetuse asjaolude tuvastamist või vältis vere alkoholisisalduse testi või joobetesti tegemist (käesolevate tingimuste tähenduses tähendab vere alkoholisisaldus või joove õigusaktides sätestatut). Rentnik peab maksma Rendileandjale alkoholijoobes (üle 0,00 promilli) või narkootiliste ainete või muude vaimset seisundit mõjutavate ainete mõju all sõitmise eest hinnakirjas nimetatud summas trahvi ka juhul, kui Rentnik andis sõiduki üle või tegi sõiduki juhtimise muul viisil võimalikuks teisele isikule, kui isik oli alkoholijoobes (üle 0,00 promilli) või narkootiliste ainete või muude vaimset seisundit mõjutavate ainete mõju all või kui isik vältis vere alkoholisisalduse testi või joobetesti tegemist;"));
    body7.addCell(getSubChapterCell("7.5.6"));
    body7.addCell(getQTextCell("sõiduki või selle juurde kuuluva vara seadusevastase omastamise või kaotsimineku korral;"));
    body7.addCell(getSubChapterCell("7.5.7"));
    body7.addCell(getQTextCell("käesolevate tingimuste või õigusaktide muude sätete rikkumise korral."));
    contractPdfDoc.add(body7);

    final var body8 = new Table(2);
    body8.setWidths(new float[] {1, 20});
    body8.setPadding(0f);
    body8.setSpacing(0f);
    body8.setWidth(100f);
    body8.setBorderColor(white);
    body8.setHorizontalAlignment(LEFT);
    body8.setBorder(NO_BORDER);
    body8.addCell(getChapterCell("VIII"));

    final var body8cell2 =
        new Cell(new Paragraph(" TRAHVIDE ERISÄTTED ", new Font(TIMES_ROMAN, 9, BOLD)));
    body8cell2.setBorder(NO_BORDER);
    body8cell2.setHorizontalAlignment(LEFT);
    body8.addCell(body8cell2);

    body8.addCell(getSubChapterCell("8.1"));

    final var body8cell4 =
        new Cell(
            new Paragraph(
                " Hinnakirjas märgitud trahv alkoholijoobes (üle 0,00 promilli) või narkootiliste ainete ja muude vaimset seisundit mõjutavate "
                    + "ainete mõju all sõitmise eest (või kui Rentnik tarvitas alkoholi või muid joovastavaid aineid pärast liiklusõnnetust enne liiklusõnnetuse asjaolude tuvastamist "
                    + "või vältis vere alkoholisisalduse testi või joobetesti tegemist) loetakse Rentniku ja Rendileandja poolt eelnevalt kokkulepitud leppetrahviks kahju põhjustamise "
                    + "eest Rendileandja mainele, firmaväärtusele ja heale nimele, kaubamärkidele ja ärinimele, Rendileandja üldjuhtimise põhimõtetele, samuti Rendileandja sotsiaalsele "
                    + "kuvandile, samuti on see mõeldud kõikide muude Rendileandjale tekitatud ebamugavuste, piirangute jms eest, mis on tingitud sellest, et Rentnik ei täida nõuetekohaselt "
                    + "tingimustes sätestatud nõudeid või jätab need täitmata. Eelnevalt nimetatud trahv tagab ka seda, et Rentnik täidab nõuetekohaselt kohustust mitte sõita alkoholijoobes "
                    + "(üle 0,00 promilli) või narkootiliste ainete või muude vaimset seisundit mõjutavate ainete mõju all, nagu on täpsemalt kirjeldatud käesolevates tingimustes, ning täidab sellega seotud ennetavat funktsiooni. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body8cell4.setBorder(NO_BORDER);
    body8cell4.setHorizontalAlignment(JUSTIFIED);
    body8.addCell(body8cell4);

    body8.addCell(getSubChapterCell("8.2"));

    final var body8cell6 =
        new Cell(
            new Paragraph(
                " Liiklusõnnetusest või kolmandaisiku õigusvastasest käitumisest tekkinud kahju kannab Rentnik ulatuses, mida ei kanna kindlustus (s.h. omavastutuse määra)."
                    + " Kui kindlustusfirma keeldub kindlustushüvitise väljamaksmisest või kui kahjujuhtum ei ole kindlustusjuhtum, kohustub Rentnik tasuma Rendileandjale sõiduki täisväärtuse ja hüvitama tekkinud kahjud.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body8cell6.setBorder(NO_BORDER);
    body8cell6.setHorizontalAlignment(JUSTIFIED);
    body8.addCell(body8cell6);

    body8.addCell(getSubChapterCell("8.3"));

    final var body8cell8 =
        new Cell(
            new Paragraph(
                " Kui rentnik annab sõidukit üle kolmandale isikule, kannab Rentnik täielikult Rendileandjale või kolmandatele isikutele tekkinud kahju.   ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body8cell8.setBorder(NO_BORDER);
    body8cell8.setHorizontalAlignment(JUSTIFIED);
    body8.addCell(body8cell8);

    body8.addCell(getSubChapterCell("8.4"));

    final var body8cell10 =
        new Cell(
            new Paragraph(
                " Kui Rendiauto on varastatud, ärandatud või röövitud, on Rentnik vastutav sõiduki täisväärtuse ulatuses ja kohustub hüvitama Rendileandjale tekitatud kahjud.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body8cell10.setBorder(NO_BORDER);
    body8cell10.setHorizontalAlignment(JUSTIFIED);
    body8.addCell(body8cell10);

    body8.addCell(getSubChapterCell("8.5"));

    final var body8cell12 =
        new Cell(
            new Paragraph(
                " Kui Rendileandjale tagastatud sõiduk vajab remonti, kannab Rentnik iga remondipäeva eest lepingus kokkulepitud rendipäeva hinnale lisaks ka remondikulud.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body8cell12.setBorder(NO_BORDER);
    body8cell12.setHorizontalAlignment(JUSTIFIED);
    body8.addCell(body8cell12);

    body8.addCell(getSubChapterCell("8.6"));

    final var body8cell14 =
        new Cell(
            new Paragraph(
                " Ebakvaliteetsest kütusest tekkinud kahjud kannab Rentnik. Kui sõiduki kütusepaaki on täidetud vale kütusega,"
                    + " hüvitab Rentnik Rendileandjale sellest tingitud kahju vastavalt kahju kõrvaldava teenusepakkuja poolt väljastatud arvele, lähtudes tegelikult tekitatud kahjust.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body8cell14.setBorder(NO_BORDER);
    body8cell14.setHorizontalAlignment(JUSTIFIED);
    body8.addCell(body8cell14);

    body8.addCell(getSubChapterCell("8.7"));

    final var body8cell16 =
        new Cell(
            new Paragraph(
                " Sõiduki dokumentide või puuduliku varustusega sõiduki tagastamisel tasub Rentnik Rendileandjale leppetrahvi 250.-EUR iga kaotatud või puuduva dokumendi või eseme kohta ja hüvitab tekkinud kahjud.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body8cell16.setBorder(NO_BORDER);
    body8cell16.setHorizontalAlignment(JUSTIFIED);
    body8.addCell(body8cell16);

    body8.addCell(getSubChapterCell("8.8"));

    final var body8cell18 =
        new Cell(
            new Paragraph(
                " Sõiduki võtmete mittetagastamisel või kaotamisel tasub Rentnik Rendileandja kahju vastavalt uue võtme soetamise ning tarkvara seadistamise arvele, mida üldjuhul väljastab autoesindus. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body8cell18.setBorder(NO_BORDER);
    body8cell18.setHorizontalAlignment(JUSTIFIED);
    body8.addCell(body8cell18);

    body8.addCell(getSubChapterCell("8.9"));

    final var body8cell20 =
        new Cell(
            new Paragraph(
                " Rentniku poolt sõiduki hävitamisel tasub Rentnik Rendileandjale leppetrahvi sõiduki soetusmaksumuse ulatuses.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body8cell20.setBorder(NO_BORDER);
    body8cell20.setHorizontalAlignment(JUSTIFIED);
    body8.addCell(body8cell20);

    body8.addCell(getSubChapterCell("8.10"));

    final var body8cell22 =
        new Cell(
            new Paragraph(
                " Rentnik on kohustatud lähtuma p.6.1.6/c - ning tasuma vara kasutamise käigus põhjustatud seaduserikkumiste"
                    + " korral kõik trahvid ja nõuded ning leppetrahvid vastavalt seaduses sätestatud korrale ning Parkimistrahvid kaasa arvatud nendega seotud kulud, "
                    + "millest Rentniku poolt Rendileandjat ei ole teavitatud ega tasutud 7 päeva jooksul, nõutakse hiljem Rentnikult sisse kolmekordselt. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body8cell22.setBorder(NO_BORDER);
    body8cell22.setHorizontalAlignment(JUSTIFIED);
    body8.addCell(body8cell22);

    body8.addCell(getSubChapterCell("8.11"));

    final var body8cell24 =
        new Cell(
            new Paragraph(
                " Kui Rentnik osaleb Rendisõidukiga liiklusõnnetuses, mille tõttu Rendileandja kindlustusriski koefitsent suureneb, tasub Rentnik ühekordset leppetrahvi 300.-EUR.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body8cell24.setBorder(NO_BORDER);
    body8cell24.setHorizontalAlignment(JUSTIFIED);
    body8.addCell(body8cell24);

    body8.addCell(getSubChapterCell("8.12"));

    final var body8cell26 =
        new Cell(
            new Paragraph(
                " Rentniku süül liiklusõnnetuse korral hüvitab Rentnik Rendileandjale tekitatud kahju täies mahus. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body8cell26.setBorder(NO_BORDER);
    body8cell26.setHorizontalAlignment(JUSTIFIED);
    body8.addCell(body8cell26);

    body8.addCell(getSubChapterCell("8.13"));

    final var body8cell28 =
        new Cell(
            new Paragraph(
                " Selguse huvides peab ettevõte tagama, et kõik sõidukid on kindlustatud kohustusliku mootorsõidukite kasutamise tsiviilvastutuskindlustusega, mis vastab Eesti "
                    + "liikluskindlustuse seadusele või samalaadsele kohustuslikku liikluskindlustust reguleerivale muu riigi õigusaktile. Ettevõte võib, aga ei ole kohustatud pakkuma sõidukitele täiendavat "
                    + "kindlustust (nt vabatahtlikku liikluskindlustust KASKO). Rentnikul on õigus vormistada vabatahtlikku KASKO autokindlustust enda nimelt. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body8cell28.setBorder(NO_BORDER);
    body8cell28.setHorizontalAlignment(JUSTIFIED);
    body8.addCell(body8cell28);

    contractPdfDoc.add(body8);

    final var body9 = new Table(2);
    body9.setWidths(new float[] {1, 20});
    body9.setPadding(0f);
    body9.setSpacing(0f);
    body9.setWidth(100f);
    body9.setBorderColor(white);
    body9.setHorizontalAlignment(LEFT);
    body9.setBorder(NO_BORDER);
    body9.addCell(getChapterCell("IX"));

    final var body9cell2 =
        new Cell(new Paragraph("KAHJU HINDAMINE. KAHJUKÄSITLUS ", new Font(TIMES_ROMAN, 9, BOLD)));
    body9cell2.setBorder(NO_BORDER);
    body9cell2.setHorizontalAlignment(LEFT);
    body9.addCell(body9cell2);

    body9.addCell(getSubChapterCell("9.1"));

    final var body9cell4 =
        new Cell(
            new Paragraph(
                "Kui Rendileandja kannab kahju (välja arvatud juhul, kui kahjusumma sisaldub poolte vahel eelnevalt kokkulepitud leppetrahvis (trahvides), "
                    + "mille summad on märgitud hinnakirjas ja või muus kokkuleppes), määratakse Rendileandja poolt kantud kahju(de) summa kahjuhindaja ja/või muude teenuseosutajate kaasamise teel.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body9cell4.setBorder(NO_BORDER);
    body9cell4.setHorizontalAlignment(JUSTIFIED);
    body9.addCell(body9cell4);

    body9.addCell(getSubChapterCell("9.2"));

    final var body9cell6 =
        new Cell(
            new Paragraph(
                "Sõidukile tekitatud kahju ja Rendileandja poolt kantud kahju(d) määratakse kindlaks vastavalt sõidukite ja muu vara hindamise ja väärtuse määramise metoodikale, "
                    + "kahju hindamise metoodikale ja eeskirjadele, mida kahjukäsitluse ekspert peab vastavalt Eesti Vabariigis kehtivatele õigusaktidele sõiduki kahju hindamisel järgima.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body9cell6.setBorder(NO_BORDER);
    body9cell6.setHorizontalAlignment(JUSTIFIED);
    body9.addCell(body9cell6);

    body9.addCell(getSubChapterCell("9.3"));

    final var body9cell8 =
        new Cell(
            new Paragraph(
                "Rentnik võib 7 (seitsme) päeva jooksul esitada oma põhjendatud vastuväited Rendileandja või Rendileandja poolt kaasatud kahjukäsitluse "
                    + "eksperdi poolt teostatud kahju hindamisele, esitades Rentniku poolt kaasatud sõltumatu sertifitseeritud (litsentseeritud) hindaja kahju hindamise ja väärtuse "
                    + "hindamise aruande, mis vastab sellise hindamise ja dokumentide suhtes kohaldatavatele õigusnõuetele (edaspidi nimetatud „alternatiivne kahjuaruanne“). "
                    + "Rentniku esitatud alternatiivset kahjuaruannet ja muid Rentniku poolt Rendileandjale esitatud dokumente hinnatakse koos muu Rendileandja ja Rendileandja poolt "
                    + "kaasatud kahjukäsitluse eksperdi ja muude teenuseosutajate poolt kogutud ja koostatud teabega. Kui pooltel on kahjusumma osas mistahes lahkarvamusi, esitab "
                    + "küsimuses lõpliku järelduse Rendileandja poolt kaasatud kahjukäsitluse ekspert, kelle järeldused on Rendileandjale ja Rentnikule kohustuslikud. "
                    + "Rentnik katab kõik alternatiivse kahjuaruande ja Rentniku või kolmandate isikute poolt palgatud sõltumatu kahjuhindaja tööga seotud kulud.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body9cell8.setBorder(NO_BORDER);
    body9cell8.setHorizontalAlignment(JUSTIFIED);
    body9.addCell(body9cell8);

    body9.addCell(getSubChapterCell("9.4"));

    final var body9cell10 =
        new Cell(
            new Paragraph(
                "Rentnik katab Rendileandja poolt kantud kahju hindamise, korrigeerimise ja haldamise kulud, samuti kõik alternatiivse või täiendava uurimise või kahju hindamisega seotud kulud. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body9cell10.setBorder(NO_BORDER);
    body9cell10.setHorizontalAlignment(JUSTIFIED);
    body9.addCell(body9cell10);

    body9.addCell(getSubChapterCell("9.5"));

    final var body9cell12 =
        new Cell(
            new Paragraph(
                "Rentnik kannab kõik riigi poolt liikluseeskirjade rikkumise eest määratud trahvid isegi juhul, kui sõidukit ei juhtinud Rentnik. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body9cell12.setBorder(NO_BORDER);
    body9cell12.setHorizontalAlignment(JUSTIFIED);
    body9.addCell(body9cell12);

    body9.addCell(getSubChapterCell("9.6"));

    final var body9cell14 =
        new Cell(
            new Paragraph(
                "Tingimustes sätestatud juhtudel katab Rentnik ka Rendileandja poolt kantud kulud seoses Rentnik poolt põhjustatud kahju või"
                    + " võlgnevuse haldamisega, välja arvatud juhul, kui sellised kulud katab juba hinnakirjas märgitud trahvisumma.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body9cell14.setBorder(NO_BORDER);
    body9cell14.setHorizontalAlignment(JUSTIFIED);
    body9.addCell(body9cell14);

    contractPdfDoc.add(body9);

    final var body10 = new Table(2);
    body10.setWidths(new float[] {1, 20});
    body10.setPadding(0f);
    body10.setSpacing(0f);
    body10.setWidth(100f);
    body10.setBorderColor(white);
    body10.setHorizontalAlignment(LEFT);
    body10.setBorder(NO_BORDER);
    body10.addCell(getChapterCell("X"));

    final var body10cell2 =
        new Cell(new Paragraph("VÄÄRTEOTRAHVID, MAKSUD JA TASUD ", new Font(TIMES_ROMAN, 9, BOLD)));
    body10cell2.setBorder(NO_BORDER);
    body10cell2.setHorizontalAlignment(LEFT);
    body10.addCell(body10cell2);

    body10.addCell(getSubChapterCell("10.1"));

    final var body10cell4 =
        new Cell(
            new Paragraph(
                "Kõik haldus- või muud liiki trahvid, maksud, tasud, muud tasumisele kuuluvad summad, mis tulenevad sõiduki ebaõigest ja (või) ebaseaduslikust "
                    + "kasutamisest või õigusaktide rikkumisest Rentnik poolt, kannab Rentnik. Juhul, kui haldus- või muud liiki trahvid, maksud, tasud, muud tasumisele kuuluvad summad "
                    + "nõutakse sisse Rendileandjalt, on Rendileandjal regressiõigus, et need summad Rentnikult täies ulatuses automaatselt kätte saada ja tagasi nõuda. Pärast politseilt ja "
                    + "teistelt pädevatelt asutustelt ning juriidilistelt isikutelt liiklusrikkumiste või muude rikkumiste, päringute või taotluste kohta teabe saamist annab ettevõte "
                    + "nendele teavet konkreetse Rentnik kui isiku kohta, kes kasutas vastavat sõidukit konkreetsel teenuste kasutamise ajal.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body10cell4.setBorder(NO_BORDER);
    body10cell4.setHorizontalAlignment(JUSTIFIED);
    body10.addCell(body10cell4);

    contractPdfDoc.add(body10);

    final var body11a = new Table(2);
    body11a.setWidths(new float[] {1, 20});
    body11a.setPadding(0f);
    body11a.setSpacing(0f);
    body11a.setWidth(100f);
    body11a.setBorderColor(white);
    body11a.setHorizontalAlignment(LEFT);
    body11a.setBorder(NO_BORDER);
    body11a.addCell(getChapterCell("XI"));

    final var body11acell2 =
        new Cell(new Paragraph("RENDILEANDJA VASTUTUS ", new Font(TIMES_ROMAN, 9, BOLD)));
    body11acell2.setBorder(NO_BORDER);
    body11acell2.setHorizontalAlignment(LEFT);
    body11a.addCell(body11acell2);

    body11a.addCell(getSubChapterCell("11.1"));

    final var body11acell4 =
        new Cell(
            new Paragraph(
                "Rendileandja vastutab käesolevates tingimustes sätestatud kohustuste täitmise eest ja hüvitab "
                    + "Rentnikule Rendileandja kohustuste mittenõuetekohase täitmise tõttu tekkinud kahju üksnes juhul, kui kahju on tekkinud Rendileandja süül.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body11acell4.setBorder(NO_BORDER);
    body11acell4.setHorizontalAlignment(JUSTIFIED);
    body11a.addCell(body11acell4);

    body11a.addCell(getSubChapterCell("11.2"));

    final var body11acell6 =
        new Cell(
            new Paragraph(
                "Ilma et see piiraks ülaltoodud sätteid, ei vastuta Rendileandja kohaldatava seadusega lubatud ulatuses järgmise eest:",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body11acell6.setBorder(NO_BORDER);
    body11acell6.setHorizontalAlignment(JUSTIFIED);
    body11a.addCell(body11acell6);
    body11a.addCell(getSubChapterCell("11.2.1"));

    final var body11acell8 =
        new Cell(
            new Paragraph(
                "kahju, mida Rentnik kandis hilinemise (nt teatud kohta saabumisel hilinemine jms), teatud kuupäeva või kellaaja unustamise tõttu seoses renditeenuste kasutamisega või renditeenuste kasutamise võimatuse tõttu;",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body11acell8.setBorder(NO_BORDER);
    body11acell8.setHorizontalAlignment(JUSTIFIED);
    body11a.addCell(body11acell8);
    body11a.addCell(getSubChapterCell("11.2.2"));

    final var body11acell10 =
        new Cell(
            new Paragraph(
                "kahju, mida Rentnik põhjustas renditeenuste kasutamisega kolmandatele isikutele või nende varale; ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body11acell10.setBorder(NO_BORDER);
    body11acell10.setHorizontalAlignment(JUSTIFIED);
    body11a.addCell(body11acell10);
    body11a.addCell(getSubChapterCell("11.2.3"));

    final var body11acell12 =
        new Cell(
            new Paragraph(
                "kahju Rentniku varale, tervisele või elule renditeenuste kasutamisel; ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body11acell12.setBorder(NO_BORDER);
    body11acell12.setHorizontalAlignment(JUSTIFIED);
    body11a.addCell(body11acell12);
    body11a.addCell(getSubChapterCell("11.2.4"));

    final var body11acell14 =
        new Cell(
            new Paragraph(
                "saamata jäänud tulu, sissetulek, äritegevus, kokkulepete või lepingute sõlmimise võimalus, tarkvara, "
                    + "andmete või teabe kasutamise võimaluse kahjustumine või kaotsiminek, maine kaotamine või kahjustumine; ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body11acell14.setBorder(NO_BORDER);
    body11acell14.setHorizontalAlignment(JUSTIFIED);
    body11a.addCell(body11acell14);
    body11a.addCell(getSubChapterCell("11.2.5"));

    final var body11acell16 =
        new Cell(
            new Paragraph(
                "kahju, mida Rentnik kandis seetõttu, et ei saanud sõidukit liiklusõnnetuse tõttu või muudel Rendileandjast sõltumatutel põhjustel kasutada; ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body11acell16.setBorder(NO_BORDER);
    body11acell16.setHorizontalAlignment(JUSTIFIED);
    body11a.addCell(body11acell16);

    contractPdfDoc.add(body11a);

    final var body12a = new Table(2);
    body12a.setWidths(new float[] {2, 35});
    body12a.setPadding(0f);
    body12a.setSpacing(0f);
    body12a.setWidth(100f);
    body12a.setBorderColor(white);
    body12a.setHorizontalAlignment(LEFT);
    body12a.setBorder(NO_BORDER);
    body12a.addCell(getChapterCell("XII"));

    final var body12acell2 =
        new Cell(
            new Paragraph(
                "TEENUSTE HIND. LISATASUD. MAKSETINGIMUSED", new Font(TIMES_ROMAN, 9, BOLD)));
    body12acell2.setBorder(NO_BORDER);
    body12acell2.setHorizontalAlignment(LEFT);
    body12a.addCell(body12acell2);

    body12a.addCell(getSubChapterCell("12.1"));

    final var body12acell4 =
        new Cell(
            new Paragraph(
                "Rendiauto lepinguline nädala renditasu suurus lepitakse kokku üleandmise-vastuvõtmise hetkel ja sõltub üleantava auto omadustest ja määratakse eraldi auto üleandmise-vastuvõtmise aktis. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell4.setBorder(NO_BORDER);
    body12acell4.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell4);

    body12a.addCell(getSubChapterCell("12.2"));

    final var body12acell6 =
        new Cell(
            new Paragraph(
                "Üleandmise-vastuvõtmise aktis määratud renditasu on aktuaalne vaid täisrendinädala eest tasumise puhul.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell6.setBorder(NO_BORDER);
    body12acell6.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell6);

    body12a.addCell(getSubChapterCell("12.3"));

    final var body12acell8 =
        new Cell(
            new Paragraph(
                "Täisrendinädalat peetakse ajaperioodi iga kalendrinädala Esmaspäeva kella 10:00'st kuni järgneva kalendrinädala"
                    + " kella 10:00'ni - nimelt seitse päeva, millest on Pühapäev tasuta ja võib olla kasutatud puhkepäevana. Iga osalise autorendinädala ööpäeva maksumus, "
                    + "mida peetakse iga kalendripäeva kella 10:00'st kuni järgneva kalendripäeva kella 10:00'ni ajaperioodi,   on võrdne täisrendinädala rendimaksumusest viiendikuga.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell8.setBorder(NO_BORDER);
    body12acell8.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell8);

    body12a.addCell(getSubChapterCell("12.4"));

    final var body12acell10 =
        new Cell(
            new Paragraph(
                "Rentnik kohustub tasuma rendileandjale ettemaksu iga tuleva nädala rendi eest sularahas Rendileandja kontoris,"
                    + " mis asub aadressil Lasnamäe 30a, Tallinn, või ülekandega Rendileandja pangakontole (või muule Rendileandja esindajaga maaratud pangakontole) "
                    + "asjakohase selgitusega – ”autorent + auto number”, mitte hiljem, kui iga nädala teisipäeva kella 16:00’ni.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell10.setBorder(NO_BORDER);
    body12acell10.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell10);

    body12a.addCell(getSubChapterCell("12.5"));

    final var body12acell12 =
        new Cell(
            new Paragraph(
                "Rentnik kohustub maksma tasu rendiperioodi eest. Renditasu ja muu rendiauto kasutamisest tulenevad kohustused hilinenud tasumise puhul on rentnik kohustatud tasuma viivist  0,1% kalendripäevas tasumata summalt.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell12.setBorder(NO_BORDER);
    body12acell12.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell12);

    body12a.addCell(getSubChapterCell("12.6"));

    final var body12acell14 =
        new Cell(
            new Paragraph(
                "Rendileandjal on õigus omal äranägemisel määrata Rentnikule renditeenuste kasutamiseks maksimaalne võlalimiit. "
                    + "Rendiandjal on ainuõigus ühepoolselt omal äranägemisel seda limiiti igal ajal muuta, tühistada, vähendada või suurendada teavitades sellest Rentniku. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell14.setBorder(NO_BORDER);
    body12acell14.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell14);

    body12a.addCell(getSubChapterCell("12.7"));

    final var body12acell16 =
        new Cell(
            new Paragraph(
                "Võlalimiit on 240 eurot, mille ületamisel võib Rendileandja renditeenuse osutamist peatada ja anda"
                    + " täiendava tähtaega kohustuse täitmiseks 14 päeva ning loeb lepingu oluliselt rikutuks juhul, kui Rentnik 14 päeva jooksul ei täida oma kohustused.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell16.setBorder(NO_BORDER);
    body12acell16.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell16);

    body12a.addCell(getSubChapterCell("12.8"));

    final var body12acell18 =
        new Cell(
            new Paragraph(
                "Iganädalase rendimaksete aluseks on leping ja seal sätestatud tasumise tingimused ning auto üleandmise-vastuvõtmise akt. "
                    + "(Rendileping sõiduauto taksoteenuse ja majandustegevuse kasutamiseks). Rentnikule väljastatud arved omavad informatiivsed tähendust ning on aruanne möödunud perioodi eest. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell18.setBorder(NO_BORDER);
    body12acell18.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell18);

    body12a.addCell(getSubChapterCell("12.9"));

    final var body12acell20 =
        new Cell(
            new Paragraph(
                "Rendileandja väljastab arve möödunud perioodi/nädala eest võttes arvesse Rentniku täidetud ja/või täitmata "
                    + "jäetud kohustused viivitusega 2 nädalad. Arve annab informatsiooni täidetud ja või täitmata jäetud lepinguliste kohustuse kohta. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell20.setBorder(NO_BORDER);
    body12acell20.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell20);

    body12a.addCell(getSubChapterCell("12.10"));

    final var body12acell22 =
        new Cell(
            new Paragraph(
                "Rendileandja väljastab ja esitab Rentnikule arved kõikide hinnakirjas märgitud trahvide, lisatasude ja muude summade eest vastavalt õigusaktides sätestatud korrale. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell22.setBorder(NO_BORDER);
    body12acell22.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell22);

    body12a.addCell(getSubChapterCell("12.11"));

    final var body12acell24 =
        new Cell(
            new Paragraph(
                "Rendileandja poolt väljastatud arvete saamisel peab Rentnik 3 (kolme) päeva jooksul kontrollima, et arved on õiged ja mittevastavuste avastamisel "
                    + "ettevõtet sellest teavitama. Rentnik peab esitama kõik arvel toodud teabega seotud nõuded 3 (kolme) päeva jooksul pärast arve saamist. Kui Rentnik ei esita mistahesnõudeid "
                    + "ülalnimetatud tähtaja jooksul, loetakse, et Rentnik tagasivõtmatult aktsepteerib väljastatud arvet.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell24.setBorder(NO_BORDER);
    body12acell24.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell24);

    body12a.addCell(getSubChapterCell("12.12"));

    final var body12acell26 =
        new Cell(
            new Paragraph(
                "Kui kõikide nimetatud ajavahemikute jooksul kliendile osutatud teenuste eest on täielikult tasutud, märgitakse Rentnikule saadetavas teates,"
                    + " et tema poolt tasumisele kuuluv jääk on 0,00 eurot. Igal muul juhul märgitakse Rentnikule saadetavas teates tema poolt tasumisele kuuluv teenuste hinna jääk koos viivistega.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell26.setBorder(NO_BORDER);
    body12acell26.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell26);

    body12a.addCell(getSubChapterCell("12.13"));

    final var body12acell28 =
        new Cell(
            new Paragraph(
                "Kui Rentnik ei tasu osutatud teenuste eest õigeaegselt ja ei tee seda Rendileandja poolt määratud mõistliku lisatähtaja jooksul, on Rendileandjal "
                    + "õigus volitada võla sissenõudmiseks inkassofirma või loovutada oma nõudeõigus Rentniku suhtes inkassofirmale või muule majandusüksusele. Ettevõte võib edastada Rendileandjal "
                    + "olevad Rentniku isikuandmed võlgade sissenõudmise, asjaajamise, kahju hindamise ja haldamise eesmärgil või muudel sarnastel eesmärkidel riigiasutustele (sealhulgas kohtutele)"
                    + " ja/või kohtutäituritele, muudele isikutele ja asutustele, kellel on õigus selliseid andmeid saada ja töödelda. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell28.setBorder(NO_BORDER);
    body12acell28.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell28);

    body12a.addCell(getSubChapterCell("12.14"));

    final var body12acell30 =
        new Cell(
            new Paragraph(
                "Kõik Rentnik poolt käesolevate tingimuste alusel Rendileandjale tasumisele kuuluvad summad tasutakse, debiteeritakse ja tasaarvestatakse järgmises järjekorras: ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell30.setBorder(NO_BORDER);
    body12acell30.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell30);
    body12a.addCell(getSubChapterCell("12.14.1"));

    final var body12acell32 =
        new Cell(new Paragraph("trahvid ja viivised; ", new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell32.setBorder(NO_BORDER);
    body12acell32.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell32);
    body12a.addCell(getSubChapterCell("12.14.2"));

    final var body12acell34 =
        new Cell(
            new Paragraph(
                "muud Rendileandjale makstavad tasud, maksud ja maksed; ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell34.setBorder(NO_BORDER);
    body12acell34.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell34);
    body12a.addCell(getSubChapterCell("12.14.3"));

    final var body12acell36 =
        new Cell(
            new Paragraph("võlgnevus osutatud teenuste eest;", new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell36.setBorder(NO_BORDER);
    body12acell36.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell36);
    body12a.addCell(getSubChapterCell("12.14.4"));

    final var body12acell38 =
        new Cell(new Paragraph("jooksev renditasu.", new Font(TIMES_ROMAN, 8, NORMAL)));
    body12acell38.setBorder(NO_BORDER);
    body12acell38.setHorizontalAlignment(JUSTIFIED);
    body12a.addCell(body12acell38);

    contractPdfDoc.add(body12a);

    //// 13
    final var body13a = new Table(2);
    body13a.setWidths(new float[] {2, 35});
    body13a.setPadding(0f);
    body13a.setSpacing(0f);
    body13a.setWidth(100f);
    body13a.setBorderColor(white);
    body13a.setHorizontalAlignment(LEFT);
    body13a.setBorder(NO_BORDER);
    body13a.addCell(getChapterCell("XIII"));

    final var body13acell2 =
        new Cell(new Paragraph("LEPINGU LÕPETAMINE", new Font(TIMES_ROMAN, 9, BOLD)));
    body13acell2.setBorder(NO_BORDER);
    body13acell2.setHorizontalAlignment(LEFT);
    body13a.addCell(body13acell2);

    body13a.addCell(getSubChapterCell("13.1"));

    final var body13acell4 =
        new Cell(
            new Paragraph(
                "Rentnikul on õigus leping igal ajal mistahes põhjusel lõpetada esitades Rendileandjale selle kohta kirjaliku teate. "
                    + "Rendileandja lõpetab lepingu Rentnikult lepingu lõpetamise kohta teate saamist hiljemalt 7 (seitsme) päeva jooksul. Auto renditeenuste lõpetamiseks "
                    + "kohustub rentnik teavitada oma soovist renditud auto tagastada "
                    + model.getDuration1()
                    + " päeva ette järgmise esmaspäevani. Lepingu lõpetamine ei vabasta "
                    + "Rentniku enne lepingu lõpetamist tekkinud kohustuste täitmisest. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell4.setBorder(NO_BORDER);
    body13acell4.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell4);

    body13a.addCell(getSubChapterCell("13.2"));

    final var body13acell6 =
        new Cell(
            new Paragraph(
                "Lepingu lõpetamisel on Rentnik kohustatud tagastama renditud auto kirjaliku üleandmise-vastuvõtmise akti alusel.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell6.setBorder(NO_BORDER);
    body13acell6.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell6);

    body13a.addCell(getSubChapterCell("13.3"));

    final var body13acell8 =
        new Cell(
            new Paragraph(
                "Auto tagastamisel teostavad Rentnik ja Rendileandja tagastava vara ülevaatuse ning fikseerivad auto seisundi ning võimalikud puudused ja erinevused võrreldes algse vara üleandmise aktiga.   ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell8.setBorder(NO_BORDER);
    body13acell8.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell8);

    body13a.addCell(getSubChapterCell("13.4"));

    final var body13acell10 =
        new Cell(
            new Paragraph(
                "Rendileandjal on õigus lõpetada Rentnikuga sõlmitud lepingu samal päeval järgmistel juhtudel: ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell10.setBorder(NO_BORDER);
    body13acell10.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell10);
    body13a.addCell(getSubChapterCell("13.4.1"));

    final var body13acell12 =
        new Cell(
            new Paragraph(
                "sõidukit juhtis isik, kellel puudus selleks õigus;  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell12.setBorder(NO_BORDER);
    body13acell12.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell12);
    body13a.addCell(getSubChapterCell("13.4.2"));

    final var body13acell14 =
        new Cell(
            new Paragraph(
                "sõidukit kasutatakse eesmärkidel, milleks see ei ole mõeldud või ette nähtud; ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell14.setBorder(NO_BORDER);
    body13acell14.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell14);
    body13a.addCell(getSubChapterCell("13.4.3"));

    final var body13acell16 =
        new Cell(
            new Paragraph(
                "juht juhtis sõidukit alkoholijoobes (üle 0,00 promilli) või narkootiliste "
                    + "ainete või muude vaimset seisundit mõjutavate ainete mõju all (ka juhul, kui Rentnik tarvitas alkoholi või muid "
                    + "joovastavaid aineid pärast liiklusõnnetust enne liiklusõnnetuse asjaolude tuvastamist või vältis vere alkoholisisalduse testi või joobetesti tegemist); ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell16.setBorder(NO_BORDER);
    body13acell16.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell16);
    body13a.addCell(getSubChapterCell("13.4.4"));

    final var body13acell18 =
        new Cell(
            new Paragraph(
                "Rentnik põhjustas sõidukile kahju tahtlikult või raske hooletuse tõttu (nt suure kiiruse ületamise, ohtliku või hoolimatu sõitmise, muu liikluseeskirjade raske rikkumise tõttu); ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell18.setBorder(NO_BORDER);
    body13acell18.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell18);
    body13a.addCell(getSubChapterCell("13.4.5"));

    final var body13acell20 =
        new Cell(
            new Paragraph(
                "hoolimatu ja ohtliku sõitmise korral; ", new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell20.setBorder(NO_BORDER);
    body13acell20.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell20);
    body13a.addCell(getSubChapterCell("13.4.6"));

    final var body13acell22 =
        new Cell(
            new Paragraph("Rentnik lahkub õnnetuspaigast; ", new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell22.setBorder(NO_BORDER);
    body13acell22.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell22);
    body13a.addCell(getSubChapterCell("13.4.7"));

    final var body13acell24 =
        new Cell(
            new Paragraph(
                "Rentnik ei täida liikluspolitsei või muude pädevate asutuste juhiseid;",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell24.setBorder(NO_BORDER);
    body13acell24.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell24);
    body13a.addCell(getSubChapterCell("13.4.8"));

    final var body13acell26 =
        new Cell(
            new Paragraph(
                "Rentnik kasutab sõidukit kuriteo toimepanemiseks;",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell26.setBorder(NO_BORDER);
    body13acell26.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell26);
    body13a.addCell(getSubChapterCell("13.4.9"));

    final var body13acell28 =
        new Cell(
            new Paragraph(
                "Rentnik ei teavita liiklusõnnetusest ettevõtet, politseid, tuletõrjet ja/või muud pädevat asutust või teenistust; ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell28.setBorder(NO_BORDER);
    body13acell28.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell28);
    body13a.addCell(getSubChapterCell("13.4.10"));

    final var body13acell30 =
        new Cell(
            new Paragraph(
                "Rentnik rikub lepingulisi renditasumise kohustusi ning ületab tema võlg 240 eurot. Rendileandjal koos mõjus p. 12.7 on õigus lepingu lõpetada ning auto viivitamatult enda valduse saada. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell30.setBorder(NO_BORDER);
    body13acell30.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell30);
    body13a.addCell(getSubChapterCell("13.4.11"));

    final var body13acell32 =
        new Cell(
            new Paragraph(
                "Rendileandja võib lepingu VÕS § 316 alusel erakorraliselt üles öelda, kui ta on andnud üürnikule kirjalikku taasesitamist võimaldavas vormis vähemalt 14-päevase täiendava tähtaja, hoiatades, et antud tähtaja jooksul võlgnevuse tasumata jätmise korral ütleb ta lepingu üles.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell32.setBorder(NO_BORDER);
    body13acell32.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell32);
    body13a.addCell(getSubChapterCell("13.4.12"));

    final var body13acell34 =
        new Cell(
            new Paragraph(
                "Juhul, kui Rentnik ei tasu võlgnevuse täiendava tähtaja jooksul, loeb üürileandja pärast selle möödumist lepingu ülesöelduks.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell34.setBorder(NO_BORDER);
    body13acell34.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell34);
    body13a.addCell(getSubChapterCell("13.4.13"));

    final var body13acell36 =
        new Cell(
            new Paragraph(
                "Juhul, kui Rentnik viivitab tasumisega, võib üürileandja üürilepingu VÕS § 316 alusel üles öelda täiendavat tähtaega andmata, kui rentnik on võlgnevusele eelnenud aasta jooksul vähemalt kahel korral täitnud kohustused alles täiendava tähtaja jooksul või pärast seda.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell36.setBorder(NO_BORDER);
    body13acell36.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell36);
    body13a.addCell(getSubChapterCell("13.4.14"));

    final var body13acell38 =
        new Cell(
            new Paragraph(
                "Rentnik rikub raskelt käesolevaid tingimusi ja/või jätkab käesolevate tingimuste rikkumist ja/või esinevad muud objektiivsed asjaolud, mille tõttu kujutab Rentnik Rendileandja arvates ohtu teistele Rentnikutele, klientidele, ühiskonnale, Rendileandjale, sõidukile;",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell38.setBorder(NO_BORDER);
    body13acell38.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell38);
    body13a.addCell(getSubChapterCell("13.4.15"));

    final var body13acell40 =
        new Cell(
            new Paragraph(
                "õigusaktides sätestatud tingimustel.", new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell40.setBorder(NO_BORDER);
    body13acell40.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell40);

    body13a.addCell(getSubChapterCell("13.5"));

    final var body13acell42 =
        new Cell(
            new Paragraph("Üürileping lõpeb Rentniku surmaga.", new Font(TIMES_ROMAN, 8, NORMAL)));
    body13acell42.setBorder(NO_BORDER);
    body13acell42.setHorizontalAlignment(JUSTIFIED);
    body13a.addCell(body13acell42);

    contractPdfDoc.add(body13a);

    //// 14

    final var body14a = new Table(2);
    body14a.setWidths(new float[] {1, 20});
    body14a.setPadding(0f);
    body14a.setSpacing(0f);
    body14a.setWidth(100f);
    body14a.setBorderColor(white);
    body14a.setHorizontalAlignment(LEFT);
    body14a.setBorder(NO_BORDER);
    body14a.addCell(getChapterCell("XIV"));

    final var body14acell2 = new Cell(new Paragraph("LÕPPSÄTTED", new Font(TIMES_ROMAN, 9, BOLD)));
    body14acell2.setBorder(NO_BORDER);
    body14acell2.setHorizontalAlignment(LEFT);
    body14a.addCell(body14acell2);

    body14a.addCell(getSubChapterCell("14.1"));

    final var body14acell4 =
        new Cell(
            new Paragraph(
                "Rendileandjal on õigus käesolevaid tingimusi ühepoolselt muuta, teavitades Rentnikku sellest e-posti teel,"
                    + " mis on lepingus näidatud. Tingimuste muudatused jõustuvad 5 (viis) päeva pärast nende Rentnikule teatavaks tegemist. Kui Rentnik jätkab "
                    + "rendilepingu alusel  teenuseid saama kooskõlas muudetud tingimustega, loetakse Rentnik muudatustega nõustunuks.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body14acell4.setBorder(NO_BORDER);
    body14acell4.setHorizontalAlignment(JUSTIFIED);
    body14a.addCell(body14acell4);

    body14a.addCell(getSubChapterCell("14.2"));

    final var body14acell6 =
        new Cell(
            new Paragraph(
                "Käesolevate tingimuste tähenduses loetakse Rentnik nõuetekohaselt kirjalikult teavitatuks järgmisel päeval pärast seda, kui Rentnikule saadetakse tema lepingus märgitud e-posti aadressile e-kirjaga saadetav teade. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body14acell6.setBorder(NO_BORDER);
    body14acell6.setHorizontalAlignment(JUSTIFIED);
    body14a.addCell(body14acell6);

    body14a.addCell(getSubChapterCell("14.3"));

    final var body14acell8 =
        new Cell(
            new Paragraph(
                "Rendileandjal on õigus anda ühepoolselt kõik või mõned käesolevatest tingimustest ja/või lepingust tulenevad õigused ja kohustused üle kolmandale isikule, A. olles teavitanud Rentnikut sellest kirjalikult ette saates üldise teate e-posti teel.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body14acell8.setBorder(NO_BORDER);
    body14acell8.setHorizontalAlignment(JUSTIFIED);
    body14a.addCell(body14acell8);

    body14a.addCell(getSubChapterCell("14.4"));

    final var body14acell10 =
        new Cell(
            new Paragraph(
                "Kõik pooltevahelised vaidlused ja lahkarvamused lahendatakse Eesti Vabariigi pädevas kohtus. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body14acell10.setBorder(NO_BORDER);
    body14acell10.setHorizontalAlignment(JUSTIFIED);
    body14a.addCell(body14acell10);

    body14a.addCell(getSubChapterCell("14.5"));

    final var body14acell12 =
        new Cell(
            new Paragraph(
                "Käesolevaid tingimusi tõlgendatakse ja kohaldatakse kooskõlas Eesti Vabariigi õigusega.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body14acell12.setBorder(NO_BORDER);
    body14acell12.setHorizontalAlignment(JUSTIFIED);
    body14a.addCell(body14acell12);

    body14a.addCell(getSubChapterCell("14.6"));

    final var body14acell14 =
        new Cell(
            new Paragraph(
                "Rentnik võib mistahes ja kõikides käesolevaid tingimusi puudutavates küsimustes pöörduda Rendileandja poole lepingus toodud rekvisiitide ja kontaktide järgi. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body14acell14.setBorder(NO_BORDER);
    body14acell14.setHorizontalAlignment(JUSTIFIED);
    body14a.addCell(body14acell14);

    contractPdfDoc.add(body14a);

    ///// 15
    final var body15a = new Table(2);
    body15a.setWidths(new float[] {1, 20});
    body15a.setPadding(0f);
    body15a.setSpacing(0f);
    body15a.setWidth(100f);
    body15a.setBorderColor(white);
    body15a.setHorizontalAlignment(LEFT);
    body15a.setBorder(NO_BORDER);
    body15a.setBorder(NO_BORDER);

    final var body15acell1 = new Cell(new Paragraph("Lisa ", new Font(TIMES_ROMAN, 9, BOLD)));
    body15acell1.setBorder(NO_BORDER);
    body15acell1.setHorizontalAlignment(LEFT);
    body15a.addCell(body15acell1);

    final var body15acell2 =
        new Cell(
            new Paragraph("nr 1 p. IIX Trahvid ja kahjutasud", new Font(TIMES_ROMAN, 9, BOLD)));
    body15acell2.setBorder(NO_BORDER);
    body15acell2.setHorizontalAlignment(LEFT);
    body15a.addCell(body15acell2);

    final var body15acell3 = new Cell(new Paragraph("A.", new Font(TIMES_ROMAN, 9, NORMAL)));
    body15acell3.setBorder(NO_BORDER);
    body15acell3.setHorizontalAlignment(LEFT);
    body15a.addCell(body15acell3);

    final var body15acell4 =
        new Cell(
            new Paragraph(
                "Kui sõiduk antakse Rentnikule üle seest ja väljast puhtana ja pestuna, kohustub rentnik tagastama sõiduki samas seisukorras."
                    + " Pesemata sõiduki tagastamisel tuleb Rentnikul tasuda trahvi välispesu vajaduse eest 60.- EUR, salongi puhastamise vajaduse eest 180.- EUR "
                    + "ja vajadusel pakiruumi puhastamise vajaduse eest 40.- EUR.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body15acell4.setBorder(NO_BORDER);
    body15acell4.setHorizontalAlignment(JUSTIFIED);
    body15a.addCell(body15acell4);

    final var body15acell5 = new Cell(new Paragraph("B.", new Font(TIMES_ROMAN, 9, NORMAL)));
    body15acell5.setBorder(NO_BORDER);
    body15acell5.setHorizontalAlignment(LEFT);
    body15a.addCell(body15acell5);

    final var body15acell6 =
        new Cell(
            new Paragraph(
                "Juhul kui sõiduk vajab keemilist puhastust, tuleb Rentnikul tasuda trahvi keemilise puhastuse vajaduse eest 360.- EUR. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body15acell6.setBorder(NO_BORDER);
    body15acell6.setHorizontalAlignment(JUSTIFIED);
    body15a.addCell(body15acell6);

    final var body15acell7 = new Cell(new Paragraph("C.", new Font(TIMES_ROMAN, 9, NORMAL)));
    body15acell7.setBorder(NO_BORDER);
    body15acell7.setHorizontalAlignment(LEFT);
    body15a.addCell(body15acell7);

    final var body15acell8 =
        new Cell(
            new Paragraph(
                "sõidukis suitsetamise eest 500 EUR  ", new Font(TIMES_ROMAN, 8, NORMAL)));
    body15acell8.setBorder(NO_BORDER);
    body15acell8.setHorizontalAlignment(JUSTIFIED);
    body15a.addCell(body15acell8);

    final var body15acell9 = new Cell(new Paragraph("D.", new Font(TIMES_ROMAN, 9, NORMAL)));
    body15acell9.setBorder(NO_BORDER);
    body15acell9.setHorizontalAlignment(LEFT);
    body15a.addCell(body15acell9);

    final var body15acell10 =
        new Cell(
            new Paragraph(
                "D.\tsõiduki juhtimise eest alkoholijoobes (üle 0,00 promilli), narkootiliste ja muude psühhotroopsete ainete mõju all "
                    + "(või kui tarvitasite alkoholi või muid joovastavaid aineid pärast liiklusõnnetust, enne kui õnnetuse asjaolud välja selgitati, või vältisite vere"
                    + " alkoholisisalduse mõõtmist või joobetesti tegemist (vere alkoholisisaldust ja joovet mõistetakse nii, nagu on määratletud õigusaktides). "
                    + "Viidatud summas trahvi alkoholijoobes (üle 0,00 promillise), narkootiliste ja muude psühhotroopsete ainete mõju all sõiduki juhtimise eest peate "
                    + "meile tasuma ka nendel juhtudel, kui võõrandasite Sõiduki või muul viisil võimaldasite teisel isikul seda juhtida, kui ta oli alkoholijoobes "
                    + "(üle 0,00 promilli), narkootiliste ja muude psühhotroopsete ainete mõju all, või kui see isik vältis vere alkoholisisalduse mõõtmist või joobetesti tegemist: 2000 EUR  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body15acell10.setBorder(NO_BORDER);
    body15acell10.setHorizontalAlignment(JUSTIFIED);
    body15a.addCell(body15acell10);

    final var body15acell11 = new Cell(new Paragraph("E.", new Font(TIMES_ROMAN, 9, NORMAL)));
    body15acell11.setBorder(NO_BORDER);
    body15acell11.setHorizontalAlignment(LEFT);
    body15a.addCell(body15acell11);

    final var body15acell12 =
        new Cell(
            new Paragraph(
                "Ebakvaliteetsest kütusest tekkinud kahjud kannab Rentnik vastavalt arvele, mida väljastab teenuse osutaja remondi teostamiseks.  ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body15acell12.setBorder(NO_BORDER);
    body15acell12.setHorizontalAlignment(JUSTIFIED);
    body15a.addCell(body15acell12);

    final var body15acell13 = new Cell(new Paragraph("F.", new Font(TIMES_ROMAN, 9, NORMAL)));
    body15acell13.setBorder(NO_BORDER);
    body15acell13.setHorizontalAlignment(LEFT);
    body15a.addCell(body15acell13);

    final var body15acell14 =
        new Cell(
            new Paragraph(
                "Sõiduki dokumentide mittetagastamisel või puuduliku varustusega sõiduki tagastamisel tasub Rentnik Rendileandjale leppetrahvi 250.-EUR iga kaotatud või puuduva dokumendi või eseme kohta ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body15acell14.setBorder(NO_BORDER);
    body15acell14.setHorizontalAlignment(JUSTIFIED);
    body15a.addCell(body15acell14);

    final var body15acell15 = new Cell(new Paragraph("G.", new Font(TIMES_ROMAN, 9, NORMAL)));
    body15acell15.setBorder(NO_BORDER);
    body15acell15.setHorizontalAlignment(LEFT);
    body15a.addCell(body15acell15);

    final var body15acell16 =
        new Cell(
            new Paragraph(
                " Võtmete kaotamise eest kannab Rentnik trahvi vastavalt esinduse poolt väljastatud arvele, mis sisaldab uue võtme ja auto signalisatsiooni ümberprogrammeerimine ja seadistamine.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body15acell16.setBorder(NO_BORDER);
    body15acell16.setHorizontalAlignment(JUSTIFIED);
    body15a.addCell(body15acell16);

    final var body15acell17 = new Cell(new Paragraph("H.", new Font(TIMES_ROMAN, 9, NORMAL)));
    body15acell17.setBorder(NO_BORDER);
    body15acell17.setHorizontalAlignment(LEFT);
    body15a.addCell(body15acell17);

    final var body15acell18 =
        new Cell(
            new Paragraph(
                " Rentniku poolt ja süül sõiduki hävitamisel tasub Rentnik Rendileandjale leppetrahvi sõiduki turuväärtuse ulatuses.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body15acell18.setBorder(NO_BORDER);
    body15acell18.setHorizontalAlignment(JUSTIFIED);
    body15a.addCell(body15acell18);

    final var body15acell19 = new Cell(new Paragraph("I.", new Font(TIMES_ROMAN, 9, NORMAL)));
    body15acell19.setBorder(NO_BORDER);
    body15acell19.setHorizontalAlignment(LEFT);
    body15a.addCell(body15acell19);

    final var body15acell20 =
        new Cell(
            new Paragraph(
                "Kui aga sõiduk saab kahjustada liiklusõnnetuses, mille põhjustab Rentnik (või muu isik, kellele Rentnik võimaldas sõidukit kasutada) ebakaines olekus, spordivõistlustel osaledes või muul viisil Tingimusi rikkudes, peab Rentnik tekitatud kahjud hüvitama täies ulatuses.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body15acell20.setBorder(NO_BORDER);
    body15acell20.setHorizontalAlignment(JUSTIFIED);
    body15a.addCell(body15acell20);

    final var body15acell21 = new Cell(new Paragraph("J.", new Font(TIMES_ROMAN, 9, NORMAL)));
    body15acell21.setBorder(NO_BORDER);
    body15acell21.setHorizontalAlignment(LEFT);
    body15a.addCell(body15acell21);

    final var body15acell22 =
        new Cell(
            new Paragraph(
                "Kui Rendileandjale tagastatud sõiduk vajab remonti, kannab Rentnik iga remondipäeva eest lepingus kokkulepitud rendipäeva hinnale lisaks ka remondikulud vastavalt üleandmise-vastuvõtmise aktis märgitud nädala hinnale ning remondiarvele.",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    body15acell22.setBorder(NO_BORDER);
    body15acell22.setHorizontalAlignment(JUSTIFIED);
    body15a.addCell(body15acell22);

    contractPdfDoc.add(body15a);

    final var signature = new Table(2);
    signature.setWidths(new float[] {1, 4});
    signature.setPadding(0f);
    signature.setSpacing(0f);
    signature.setWidth(100f);
    signature.setBorderColor(white);
    signature.setHorizontalAlignment(LEFT);
    signature.setBorder(NO_BORDER);
    signature.setBorder(NO_BORDER);

    final var signaturecell1 =
        new Cell(new Paragraph("RENDILEANDJA:  ", new Font(TIMES_ROMAN, 9, BOLD)));
    signaturecell1.setBorder(NO_BORDER);
    signaturecell1.setHorizontalAlignment(LEFT);
    signature.addCell(signaturecell1);

    final var signaturecell2 =
        new Cell(new Paragraph(model.getQFirmName(), new Font(TIMES_ROMAN, 9, BOLD)));
    signaturecell2.setBorder(NO_BORDER);
    signaturecell2.setHorizontalAlignment(LEFT);
    signature.addCell(signaturecell2);

    final var signaturecell3 =
        new Cell(new Paragraph("Rendileandja esindaja  ", new Font(TIMES_ROMAN, 9, BOLD)));
    signaturecell3.setBorder(NO_BORDER);
    signaturecell3.setHorizontalAlignment(LEFT);
    signature.addCell(signaturecell3);

    final var signaturecell4 =
        new Cell(
            new Paragraph(
                "__________________________________________________________________________________________  ",
                new Font(TIMES_ROMAN, 9, BOLD)));
    signaturecell4.setBorder(NO_BORDER);
    signaturecell4.setHorizontalAlignment(JUSTIFIED);
    signature.addCell(signaturecell4);

    final var signaturecell41 =
        new Cell(
            new Paragraph(
                "Alloleva allkirjaga tõendan, et olen Koostöölepingu täielikult läbi lugenud, selle sisust ja mõttest aru saanud ning nõustun nende tingimustega. ",
                new Font(TIMES_ROMAN, 8, NORMAL)));
    signaturecell41.setColspan(2);
    signaturecell41.setBorder(NO_BORDER);
    signaturecell41.setHorizontalAlignment(JUSTIFIED);
    signature.addCell(signaturecell41);

    final var signaturecell5 =
        new Cell(new Paragraph("RENTNIK:  ", new Font(TIMES_ROMAN, 9, BOLD)));
    signaturecell5.setBorder(NO_BORDER);
    signaturecell5.setHorizontalAlignment(LEFT);
    signature.addCell(signaturecell5);

    final var signaturecell6 =
        new Cell(new Paragraph(model.getRenterName(), new Font(TIMES_ROMAN, 9, BOLD)));
    signaturecell6.setBorder(NO_BORDER);
    signaturecell6.setHorizontalAlignment(LEFT);
    signature.addCell(signaturecell6);

    final var signaturecell7 =
        new Cell(new Paragraph("Rentnik esindaja  ", new Font(TIMES_ROMAN, 9, BOLD)));
    signaturecell7.setBorder(NO_BORDER);
    signaturecell7.setHorizontalAlignment(LEFT);
    signature.addCell(signaturecell7);

    final var signaturecell8 =
        new Cell(
            new Paragraph(
                "________________________________________________________________________      _______________  ",
                new Font(TIMES_ROMAN, 9, BOLD)));
    signaturecell8.setBorder(NO_BORDER);
    signaturecell8.setHorizontalAlignment(JUSTIFIED);
    signature.addCell(signaturecell8);

    final var signaturecell9 =
        new Cell(
            new Paragraph(
                "Nimi, allkiri                                                        Kuupäev             ",
                new Font(TIMES_ROMAN, 9, NORMAL)));
    signaturecell9.setColspan(2);
    signaturecell9.setBorder(NO_BORDER);
    signaturecell9.setHorizontalAlignment(RIGHT);
    signature.addCell(signaturecell9);

    contractPdfDoc.add(signature);

    contractPdfDoc.close();
    writer.close();

    return new ByteArrayInputStream(contractPdfOutputStream.toByteArray());
  }

  private static Cell getChapterCell(final String chapterNumber) {
    final var chapterCell =
        new Cell(new Paragraph(chapterNumber + ".", new Font(TIMES_ROMAN, 9, BOLD)));
    chapterCell.setBorder(NO_BORDER);
    chapterCell.setHorizontalAlignment(LEFT);

    return chapterCell;
  }

  private static Cell getSubChapterCell(final String subChapterNumber) {
    final var subChapterCell =
        new Cell(new Paragraph(subChapterNumber, new Font(TIMES_ROMAN, 9, NORMAL)));
    subChapterCell.setBorder(NO_BORDER);
    subChapterCell.setHorizontalAlignment(LEFT);

    return subChapterCell;
  }

  @SneakyThrows
  private static Table getHeaderTable(final ContractPdfModel model) {
    final var header = new Table(2);
    header.setWidths(new float[] {1, 7});
    header.setPadding(0f);
    header.setSpacing(0f);
    header.setWidth(100f);
    header.setBorderColor(white);
    header.setHorizontalAlignment(RIGHT);
    header.setBorder(NO_BORDER);

    final var logo = Image.getInstance("Images/qRentalGroup_gorznt.png");
    logo.scaleAbsolute(50f, 20f);
    final var logoCell = new Cell(logo);
    logoCell.setRowspan(3);
    logoCell.setBorder(NO_BORDER);
    header.addCell(logoCell);

    final var headlineCell =
        new Cell(
            new Paragraph(
                "Rendileping  sõiduauto taksoteenuse ja majandustegevuse kasutamiseks   Nr. "
                    + model.getNumber(),
                new Font(TIMES_ROMAN, 10, BOLD)));
    headlineCell.setBorder(NO_BORDER);
    headlineCell.setHorizontalAlignment(CENTER);
    header.addCell(headlineCell);

    final var dateCell =
        new Cell(new Paragraph("Data : " + model.getCreated(), new Font(TIMES_ROMAN, 10, BOLD)));
    dateCell.setBorder(NO_BORDER);
    dateCell.setHorizontalAlignment(CENTER);
    header.addCell(dateCell);

    return header;
  }

  private static Table getRenterTable(final ContractPdfModel model) {
    final var renterTable = new Table(1);
    renterTable.setPadding(0f);
    renterTable.setSpacing(0f);
    renterTable.setWidth(100f);
    renterTable.setBorderColor(white);
    renterTable.setHorizontalAlignment(LEFT);
    renterTable.setBorder(NO_BORDER);

    final var labelValue = "RENDILEANDJA ANDMED: ";
    renterTable.addCell(getQCell(labelValue));
    final var qFirmNameValue = model.getQFirmName();
    renterTable.addCell(getQCell(qFirmNameValue));
    final var hereinafterLabel = "edaspidi Rendileandja.";
    renterTable.addCell(getQCellBold(hereinafterLabel));
    final var qFirmAddressValue = "Asukoht:  " + getTextOrEmpty(model.getQFirmPostAddress());
    renterTable.addCell(getQCell(qFirmAddressValue));
    final var qFirmRegNumberValue = "Registrinumber:  " + getTextOrEmpty(model.getQFirmRegNumber());
    renterTable.addCell(getQCell(qFirmRegNumberValue));
    final var qFirmVatNumberValue = "KMKR:  " + getTextOrEmpty(model.getQFirmVatNumber());
    renterTable.addCell(getQCell(qFirmVatNumberValue));
    final var qFirmIbanValue = "Pangakonto number:  " + getTextOrEmpty(model.getQFirmIban());
    renterTable.addCell(getQCell(qFirmIbanValue));
    final var qFirmEmailValue = "E-post:  " + getTextOrEmpty(model.getQFirmEmail());
    renterTable.addCell(getQCell(qFirmEmailValue));
    final var qFirmPhoneValue = "Kontakttelefon:  " + getTextOrEmpty(model.getQFirmPhone());
    renterTable.addCell(getQCell(qFirmPhoneValue));
    final var qFirmCeoValue =
        "Rendileandja esindaja:  "
            + getTextOrEmpty(model.getQFirmCeo() + " või volitatud isik Nadežda Tarraste");
    renterTable.addCell(getQCell(qFirmCeoValue));

    return renterTable;
  }

  private static Table getTenantTable(final ContractPdfModel model) {
    final var tenantTable = new Table(1);
    tenantTable.setPadding(0f);
    tenantTable.setSpacing(0f);
    tenantTable.setWidth(100f);
    tenantTable.setBorderColor(white);
    tenantTable.setHorizontalAlignment(LEFT);
    tenantTable.setBorder(NO_BORDER);
    final var tenantDataLabel = "RENTNIKU ANDMED: ";
    tenantTable.addCell(getQCellBold(tenantDataLabel));
    final var tenantNameValue = "Rentniku nimi: " + getTextOrEmpty(model.getRenterName());
    tenantTable.addCell(getQCell(tenantNameValue));
    final var tenantRegNumberValue =
        "Rentniku reg. nr. või isikukood: " + getTextOrEmpty(model.getRenterRegistrationNumber());
    tenantTable.addCell(getQCell(tenantRegNumberValue));
    final var tenantAddressValue = "Rentniku aadress: " + getTextOrEmpty(model.getRenterAddress());
    tenantTable.addCell(getQCell(tenantAddressValue));
    final var tenantCeoNameValue =
        "Rentniku seadusliku või volitatud esindaja nimi:  "
            + getTextOrEmpty(model.getRenterCeoName());
    tenantTable.addCell(getQCell(tenantCeoNameValue));
    final var tenantCeoTaxNumberValue =
        "Rentniku seadusliku või volitatud esindaja isikukood:  " + model.getRenterCeoTaxNumber();
    tenantTable.addCell(getQCell(tenantCeoTaxNumberValue));
    final var tenantDriverLicenceNumberValue =
        "Rentniku või selle seadusliku ega volitatud esindaja juhiloa number:  "
            + getTextOrEmpty(model.getDriverLicenceNumber());
    tenantTable.addCell(getQCell(tenantDriverLicenceNumberValue));
    final var tenantDriverAddressValue =
        "Rentniku või selle seadusliku ega volitatud esindaja kontaktaadress:   "
            + getTextOrEmpty(model.getDriverAddress());
    tenantTable.addCell(getQCell(tenantDriverAddressValue));
    final var tenantPhoneValue =
        "Rentniku või selle seadusliku ega volitatud esindaja kontakttelefon:   "
            + getTextOrEmpty(model.getRenterPhone());
    tenantTable.addCell(getQCell(tenantPhoneValue));
    final var tenantEmailValue =
        "Rentniku või selle seadusliku ega volitatud esindaja e-post:   "
            + getTextOrEmpty(model.getRenterEmail());
    tenantTable.addCell(getQCell(tenantEmailValue));

    return tenantTable;
  }

  private static Cell getQCell(final String value) {
    final var cell = new Cell(new Paragraph(value, new Font(TIMES_ROMAN, 9, NORMAL)));
    cell.setBorder(NO_BORDER);
    cell.setHorizontalAlignment(LEFT);

    return cell;
  }

  private static Cell getQTextCell(final String value) {
    final var cell = new Cell(new Paragraph(value, new Font(TIMES_ROMAN, 8, NORMAL)));
    cell.setBorder(NO_BORDER);
    cell.setHorizontalAlignment(JUSTIFIED);

    return cell;
  }

  private static Cell getQCellBold(final String value) {
    final var cell = new Cell(new Paragraph(value, new Font(TIMES_ROMAN, 10, BOLD)));
    cell.setBorder(NO_BORDER);
    cell.setHorizontalAlignment(LEFT);

    return cell;
  }

  private static String getTextOrEmpty(final String text) {
    if (text == null || text.isBlank()) {
      return "---";
    }
    return text;
  }
}
