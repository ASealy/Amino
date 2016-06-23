import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class DNA_Match {
	public static void main(String[] agrs){
		
		ArrayList<DNA_Item> dna_list=new ArrayList<DNA_Item>();
		
		while(true){
			int choice=-1;
			JList list = new JList(new String[]{"Compare new DNA sequences","Sequence amino acids", "Print existing sequences", "Quit"});
			JOptionPane.showMessageDialog( null, list,"", JOptionPane.DEFAULT_OPTION);
			String input=Arrays.toString(list.getSelectedIndices());
			if(input.equals("[0]")) choice=0;
			if(input.equals("[1]")) choice=1;
			if(input.equals("[2]")) choice=2;
			if(input.equals("[3]")) choice=3;
			
			//
			if(choice==0){
				int choice2=-1;
				JList list2 = new JList(new String[]{"Paste DNA sequence","Import from text file", "Cancel"});
				JOptionPane.showMessageDialog( null, list2,"", JOptionPane.DEFAULT_OPTION);
				String input2=Arrays.toString(list2.getSelectedIndices());
				
				if(input2.equals("[0]")) choice2=0;
				if(input2.equals("[1]")) choice2=1;
				if(input2.equals("[2]")) choice2=2;
				if(choice2==0){
					String string=JOptionPane.showInputDialog("Enter DNA sequence");
					String sub=JOptionPane.showInputDialog("Enter sub-sequence");
					if(string==null||sub==null) continue;
					long number_of_matches_found=0;
					if(sub.length()<string.length()){
						long count=0;
						for(long i=0;i<string.length()-sub.length()+1;i++){
							for(long j=0;j<sub.length();j++){
								if(sub.charAt((int) j)==string.charAt((int) (i+j)))count++;
								if(count==sub.length())number_of_matches_found++;
							}	
							count=0;
						}
					}
					if(number_of_matches_found==1) JOptionPane.showMessageDialog(null, "The sub-sequence was found 1 time.");
					else JOptionPane.showMessageDialog(null, "The sub-sequence was found "+number_of_matches_found+" times.");
				}
				if(choice2==1) break;
				if(choice2==2) continue;
			}
			
			if(choice==1){
				String sequence=JOptionPane.showInputDialog("Enter DNA sequence");
				if(sequence==null) continue;
				if(sequence.length()%3==0){
					String amino_acid="";
					sequence=sequence(sequence);
					JOptionPane.showMessageDialog(null,sequence);
					DNA_Item item=new DNA_Item(sequence);
					dna_list.add(item);
				}
				else JOptionPane.showMessageDialog(null, "Invalid DNA sequence");
				
				
				
			}
			
			if(choice==2){
				if(dna_list.isEmpty()){JOptionPane.showMessageDialog(null,"There are currently no sequence entries."); continue;}
				
				else{
					String display="";
					for(int i=0;i<dna_list.size();i++){
						display+=dna_list.get(i);
					}
					JOptionPane.showMessageDialog(null,display);	
				}
			}
			
			
			
			if(choice==3) break;
			
		}
	}
	
	public static String sequence(String a){
		a=flip(a);
		String result="";
		while(a.length()!=0){
			String amino=""+a.charAt(0)+a.charAt(1)+a.charAt(2);
			result+=amino(amino);
			a=a.substring(3);
		}
		return result;
	}
	
	public static String amino(String a){
		if(a.equals("UUU")||a.equals("UUC")) return "Phe - ";
		if(a.equals("UUA")||a.equals("UUG")) return "Leu - ";
		if(a.equals("UCU")||a.equals("UCC")||a.equals("UCA")||a.equals("UCG")) return "Ser - ";
		if(a.equals("UAU")||a.equals("UAC")) return "Tyr - ";
		if(a.equals("UGU")||a.equals("UGC")) return "Cys - ";
		if(a.equals("CUU")||a.equals("CUC")||a.equals("CUA")||a.equals("CUG")) return "Leu - ";
		if(a.equals("CCU")||a.equals("CCC")||a.equals("CCA")||a.equals("CCG")) return "Pro - ";
		if(a.equals("CAU")||a.equals("CAC")) return "His - ";
		if(a.equals("CAA")||a.equals("CAG")) return "Gln - ";
		if(a.equals("CGU")||a.equals("CGC")||a.equals("CGA")||a.equals("CGG")) return "Arg - ";
		if(a.equals("AUU")||a.equals("AUC")||a.equals("AUA")) return "Ile - ";
		if(a.equals("AUG")) return "Met - ";
		if(a.equals("UGG")) return "Trp - ";
		if(a.equals("ACU")||a.equals("ACC")||a.equals("ACA")||a.equals("ACG")) return "Thr - ";
		if(a.equals("AAU")||a.equals("AAC")) return "Asn - ";
		if(a.equals("AAA")||a.equals("AAG")) return "Lys - ";
		if(a.equals("AGU")||a.equals("AGC")) return "Ser - ";
		if(a.equals("AGA")||a.equals("AGG")) return "Arg - ";
		if(a.equals("GUU")||a.equals("GUC")||a.equals("GUA")||a.equals("GUG")) return "Val - ";
		if(a.equals("GCU")||a.equals("GCC")||a.equals("GCA")||a.equals("GCG")) return "Ala - ";
		if(a.equals("GAU")||a.equals("GAC")) return "Asp - ";
		if(a.equals("GAA")||a.equals("GAG")) return "Glu - ";
		if(a.equals("GGU")||a.equals("GGC")||a.equals("GGA")||a.equals("GGG")) return "Gly - ";
		if(a.equals("UUU")||a.equals("UUC")) return "Phe - ";
		if(a.equals("UAA")||a.equals("UAG")||a.equals("UGA")) return "STOP";
		return "ERROR - ";
	}
	
	public static String flip(String a){
		String result="";
		for(int i=0;i<a.length();i++){
			if(a.charAt(i)=='A') result+="U";
			if(a.charAt(i)=='T') result+="A";
			if(a.charAt(i)=='G') result+="C";
			if(a.charAt(i)=='C') result+="G";
		}
		return result;
	}
}