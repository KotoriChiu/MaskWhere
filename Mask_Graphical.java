import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.io.*;
import java.net.*;
public class Mask_Graphical extends JFrame  {

    JTextField text;
    JButton button,button1;
    JComboBox comBox;
    JTextArea area;
    ReaderListen listener;
    ReaderListen mask = new ReaderListen();
    public Mask_Graphical() throws HeadlessException{
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void init() {
        String[] city = {"�򶩥�","�x�_��","�s�_��","��鿤","�s�˥�","�s�˿�","�]�߿�","�x����","���ƿ�","�n�뿤","���L��","�Ÿq��","�Ÿq��","�x�n��","������","�̪F��","�x�F��","�Ὤ��","�y����","���","������","�s����"};
        setLayout(new FlowLayout()); // �]�w�G��
        
        add(new JLabel("�ۭq�j�M:"));
        

        text = new JTextField(10);
        text.addActionListener(listener);
        add(text);

        button = new JButton("�ۭq�j�M");
        

        add(button);
        add(new JLabel("�̿����j�M:")); //�y����","���","������","�s����"

        comBox = new JComboBox<>();
        comBox.addActionListener(listener);
        for(int i=0;i<city.length;i++) comBox.addItem(city[i]);
        add(comBox);

        button1 = new JButton("�̿����j�M");
        add(button1);

        area = new JTextArea(25, 65);// ��r�ϳ]�w��ƩM�C��
        add(new JScrollPane(area));
        listener = new ReaderListen();
        listener.setJTextField(text);
        listener.setJTextArea(area);
        listener.SetJComboBox(comBox);
        button.addActionListener(listener);
        button1.addActionListener(listener);
        add(new JScrollPane(area));

    }
}

class ReaderListen extends Maskread implements ActionListener{
    JComboBox choice;
    JTextArea textShow;
    JTextField text;

    public void SetJComboBox(JComboBox choice){
        this.choice = choice;
    }
    public void setJTextArea(JTextArea textShow) {
        this.textShow = textShow;
    }
    public void setJTextField(JTextField text) {
        this.text = text;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        textShow.setText(null);
        String CityName = null;
        try {
            if("�̿����j�M".equals(e.getActionCommand())){
                CityName = choice.getSelectedItem().toString(); 
                textShow.append(SeartchPrint(CityName));
            }
            else if("�ۭq�j�M".equals(e.getActionCommand()))
                textShow.append(SeartchPrint(text.getText()));
        } catch (Exception e2) {
            //textShow.append(e2.toString());
            e2.printStackTrace();
        }
    }
    public String getText(){
        return text.getText();
    }
    public String getChoice(){
        return choice.getSelectedItem().toString();
    }
    static String SeartchPrint(String CityText){
        GetMASK();
        int count = 0;
        String[] Saved_mask = new String[9000];
        String RString="";
        for(int i = 0;i<mask.length;i++){
            try{
                if(mask[i][2].contains(CityText)||mask[i][1].contains(CityText)){
                    Saved_mask[count] = String.format("��n�a�I:%-20s\n�a�}:%-30s\n���H�f�n�w�s:%-5s\n�ൣ�f�n�w�s:%-5s\n����^���ɶ�:%-2s\n\n",mask[i][1],mask[i][2],mask[i][4],mask[i][5],mask[i][6]);
                    count++;
                }
            }catch(NullPointerException e){}
        }
        if(count!=0)RString+="�j�M���G �@"+(count)+"��\n";
        for(String out:Saved_mask){
            if(count==0){
                RString+=("�j�M���G �S���������� �Э��s�d��\n");
                break;
            }
            if(out=="" || out==null)break;
            RString+=(out);
        }
        return RString;
    }
}
class Maskread {
    static String[][] mask = new String[8000][7];
    static void GetMASK(){
        try{
                URL url = new URL("https://data.nhi.gov.tw/resource/mask/maskdata.csv");
                InputStream stream = url.openStream();
                InputStreamReader isr = new InputStreamReader(stream,"UTF-8");//�ɮ�Ū�����|
                BufferedReader reader = new BufferedReader(isr);
                //BufferedWriter bw = new BufferedWriter(new FileWriter("D://file_output.csv"));//�ɮ׿�X���|
                String line = null;
            
                int icecream = 0;
                while((line=reader.readLine())!=null){
                    String item[] = line.split(",");

                    for(int i = 0;i<mask[0].length;i++){
                        mask[icecream][i] = item[i].trim();
                        //System.out.println(item[i].trim());
                    }
                    icecream++;
                    //for(String simple : mask)System.out.print(simple+"\t");
                    //System.out.println();
                }
                reader.close();
        }catch(FileNotFoundException e){
        }catch (IOException e){}
    }
}
