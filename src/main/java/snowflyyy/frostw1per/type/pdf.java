package snowflyyy.frostw1per.type;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.PDPage;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.Loader;

import static snowflyyy.frostw1per.Main.message;

public class pdf {
    public static void stripMDPDF(String filePath){
        try (PDDocument document = Loader.loadPDF(new File(String.valueOf(filePath)))) {
            int pageCount = document.getNumberOfPages();
            PDDocumentInformation information = document.getDocumentInformation();
            information.getCOSObject().clear();

            PDMetadata metadata = document.getDocumentCatalog().getMetadata();
            if (metadata != null) {
                metadata.setMetadata(null);
            }

            int i;
            for (i = 0; i < pageCount; i++) {
                PDPage page = document.getPage(i);
                page.getCOSObject().removeItem(COSName.METADATA);
                page.getAnnotations().clear();
                page.setActions(null);
            }

            PDDocumentInformation info = document.getDocumentInformation();
            info.setAuthor(message);
            info.setCreator(message);
            info.setCreationDate(null);
            info.setModificationDate(null);
            info.setKeywords(message);
            info.setAuthor(message);
            info.setTitle(message);

            String cut = "/";
            String[] oldPath = filePath.split(cut);
            StringBuilder newPathCreate = new StringBuilder();
            for (int l = 0; l < oldPath.length - 1; l++) {
                newPathCreate.append(oldPath[l]);
                if (l < oldPath.length - 2) {
                    newPathCreate.append("/");
                }
            }
            newPathCreate.append("/[cleaned by snowf1ake.net]");
            String newPath = newPathCreate.toString();
            document.save(newPath);
            System.out.println("Cleaned file saved to " + newPath);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
