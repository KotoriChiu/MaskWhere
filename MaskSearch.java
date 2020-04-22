public class MaskSearch{
    public static void main(String[] args){
        //String defaultEncodingName = System.getProperty( "file.encoding" );
        System.setProperty("file.encoding", "UTF-8");
        Mask_Graphical win = new Mask_Graphical();
        win.setBounds(100,100,800,550);
        win.setTitle("口罩你在哪!!!!!!");
    }
}