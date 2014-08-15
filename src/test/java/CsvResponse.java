public class CsvResponse {

    private final String csvData;
    private final String filename;

    public CsvResponse(String csvData, String filename) {
        this.csvData = csvData;
        this.filename = filename;
    }
    public String getFilename() {
        return filename;
    }

    public String getCsvData() {
        return csvData;
    }
}
