public class Base64 {
    public String encodeBase64(String input) {
        return java.util.Base64.getEncoder().encodeToString(input.getBytes());
    }

    public String decodeBase64(String input) {
        return new String(java.util.Base64.getDecoder().decode(input));
    }

    public static void main(String[] args) {
        Base64 base64 = new Base64();
        String encoded = base64.encodeBase64("password");
        System.out.println(encoded);
        System.out.println(base64.decodeBase64(encoded));
    }
}
