
/**
 * @author lq
 * @date 2020/7/2
 */
public static void saveAsUTF8(String inputFileUrl, String outputFileUrl) throws IOException {
    String inputFileEncode = EncodingDetect.getJavaEncode(inputFileUrl);
    System.out.println("inputFileEncode===" + inputFileEncode);
    BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(new FileInputStream(inputFileUrl), inputFileEncode));
    BufferedWriter bufferedWriter = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(outputFileUrl), "UTF-8"));
    String line;
    while ((line = bufferedReader.readLine()) != null) {
        bufferedWriter.write(line + "\r\n");        }
    bufferedWriter.close();        bufferedReader.close();
    String outputFileEncode = EncodingDetect.getJavaEncode(outputFileUrl);
    System.out.println("outputFileEncode===" + outputFileEncode);
    System.out.println("txt文件格式转换完成");
}
