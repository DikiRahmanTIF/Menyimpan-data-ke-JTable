package com.kellima.pemdal;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class MEOnlineStore {
    private JTextField textbrandproduk;
    private JTextField textKustomer;
    private JTextField textharga;
    private JTextField textjumlah;
    private JButton buttonsave;
    private JTable tabelData;
    private JComboBox comboboxjenisproduk;
    private JPanel rootPanel;
    private final DataMEOstore objMEO;
    private String totalHarga;

    private DefaultTableModel tableModel;

    public MEOnlineStore() {

        this.objMEO = new DataMEOstore();
        this.initComponents();

        buttonsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getmerekproduk = textbrandproduk.getText();
                String getkustomer = textKustomer.getText();
                String getjenisproduk = Objects.requireNonNull(comboboxjenisproduk.getSelectedItem()).toString();
                int getharga = Integer.parseInt(textharga.getText());
                int getjumlahbarang = Integer.parseInt(textjumlah.getText());

                objMEO.setMerek(getmerekproduk);
                objMEO.setKustomer(getkustomer);
                objMEO.setJenisproduk(getjenisproduk);
                objMEO.setHarga(Integer.parseInt(String.valueOf(getharga)));
                objMEO.setJumlah(Integer.parseInt(String.valueOf(getjumlahbarang)));

                totalHarga = String.valueOf(getharga * getjumlahbarang);

                tableModel.addRow(new Object[]{getkustomer, getmerekproduk, getjenisproduk, getharga, getjumlahbarang, totalHarga});

                String dataInput = "\nKustomer :" + getkustomer + "\nMerek Produk : " + getmerekproduk + "\nJenis Produk : " + getjenisproduk + "\nHarga Produk : " + getharga + "\nKuantitas Pesanan : " + getjumlahbarang + "\nTotal Harga : " + totalHarga + "\n\n";
                String Temp = objMEO.getKustomer() + "#" + objMEO.getMerek() + "#" + objMEO.getJenisproduk() + "#" + objMEO.getHarga() + "#" + objMEO.getJumlah() + "#" + objMEO.hargaAkhir() + "\n";
                FileWriter fw=null;
                try {
                    fw = new FileWriter("StoredTextDatabase.txt", true);
                    fw.write(dataInput);
                    fw.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    fw = new FileWriter(System.getProperty("user.dir") + "/src/TEMPStrArr.txt", true);
                    fw.write(Temp);
                    fw.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }

        });
    }

    private void initComponents() {

        Object[] tableColumn = {"Kustomer",
                "Merek Produk",
                "Jenis Produk",
                "Harga",
                "Kuantitas Pesanan",
                "Total Harga"};

        Object[][] row = {};

        try {
            tableModel = new DefaultTableModel(new DataMEOstore().getmObject(), tableColumn);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        tabelData.setModel(tableModel);
        tabelData.setAutoCreateRowSorter(true);

        for(int i = 0; i < 6; i++) {
            TableColumn col = tabelData.getColumnModel().getColumn(i);
            DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
            dtcr.setHorizontalAlignment(SwingConstants.CENTER);
            col.setCellRenderer(dtcr);
        }
    }


    public JPanel getRoot() {
        return rootPanel;
    }
}