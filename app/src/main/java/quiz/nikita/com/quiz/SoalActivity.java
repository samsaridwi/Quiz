package quiz.nikita.com.quiz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SoalActivity extends AppCompatActivity {
    ArrayList<ModelQuiz> modelQuizs = new ArrayList<>();
    static int i= 1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        String file = getIntent().getStringExtra("file");
        Scanner scanner   = null;
        try {
            scanner = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()){
           /* String s = scanner.nextLine();
            Toast.makeText(SoalActivity.this, s, Toast.LENGTH_SHORT).show();*/
            Scanner rowScanner1  = new Scanner(scanner.nextLine()) ;
            rowScanner1.useDelimiter(";");

            ModelQuiz modelQuiz = new ModelQuiz();
            try {
               modelQuiz.setSoal(rowScanner1.next());
               modelQuiz.setJawabanA(rowScanner1.next());
               modelQuiz.setJawabanB(rowScanner1.next());
               modelQuiz.setJawabanC(rowScanner1.next());
               modelQuiz.setJawabanD(rowScanner1.next());
               modelQuiz.setJawabanBenar(rowScanner1.next());
               modelQuiz.setPoin(rowScanner1.next());
               modelQuiz.setSoalGambar(rowScanner1.next());
           }catch (Exception e){
                e.printStackTrace();
            }
            modelQuizs.add(modelQuiz);
        }


        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerView.setAdapter(new RecyclerView.Adapter() {
             public int getItemViewType(int position) { return position; }
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
                   View v ;
                   if (modelQuizs.get(position).getSoalGambar().startsWith("http")){
                       v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_soal_gmbr, viewGroup, false);
                       Picasso.get().load(modelQuizs.get(position).getSoalGambar()).into((ImageView) v.findViewById(R.id.img_soal));
                       
                   }else{
                       v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_soal_txt, viewGroup, false);
                   }

                final RadioButton radioButtonA =  v.findViewById(R.id.rb_A);
                final RadioButton radioButtonB =  v.findViewById(R.id.rb_B);
                final RadioButton radioButtonC =  v.findViewById(R.id.rb_C);
                final RadioButton radioButtonD =  v.findViewById(R.id.rb_D);
                radioButtonA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                radioButtonB.setChecked(false);
                                radioButtonC.setChecked(false);
                                radioButtonD.setChecked(false);
                            }
                        }
                });
                radioButtonB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            radioButtonA.setChecked(false);
                            radioButtonC.setChecked(false);
                            radioButtonD.setChecked(false);
                        }
                    }
                });
                radioButtonC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            radioButtonB.setChecked(false);
                            radioButtonA.setChecked(false);
                            radioButtonD.setChecked(false);
                        }

                    }
                });
                radioButtonD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            radioButtonB.setChecked(false);
                            radioButtonC.setChecked(false);
                            radioButtonA.setChecked(false);
                        }

                    }
                });


                return new RecyclerView.ViewHolder(v) { };
            }

            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
               TextView

                       textView = viewHolder.itemView.findViewById(R.id.txtSoal);
                textView.setText(modelQuizs.get(position).getSoal());
                textView = viewHolder.itemView.findViewById(R.id.rb_A);
                textView.setText(modelQuizs.get(position).getJawabanA());
                textView = viewHolder.itemView.findViewById(R.id.rb_B);
                textView.setText(modelQuizs.get(position).getJawabanB());
                textView = viewHolder.itemView.findViewById(R.id.rb_C);
                textView.setText(modelQuizs.get(position).getJawabanC());
                textView = viewHolder.itemView.findViewById(R.id.rb_D);
                textView.setText(modelQuizs.get(position).getJawabanD());


            }

            @Override
            public int getItemCount() {
                return modelQuizs.size();
            }
        });

    }

    class ModelQuiz{
        String soal;
        String soalGambar;

        public String getJawabanBenar() {
            return jawabanBenar;
        }

        public void setJawabanBenar(String jawabanBenar) {
            this.jawabanBenar = jawabanBenar;
        }

        String jawabanBenar;

        public String getPoin() {
            return poin;
        }

        public void setPoin(String poin) {
            this.poin = poin;
        }

        String poin;
        public String getSoal() {
            return soal;
        }

        public void setSoal(String soal) {
            this.soal = soal;
        }

        public String getSoalGambar() {
            return soalGambar;
        }

        public void setSoalGambar(String soalGambar) {
            this.soalGambar = soalGambar;
        }

        public String getJawabanA() {
            return jawabanA;
        }

        public void setJawabanA(String jawabanA) {
            this.jawabanA = jawabanA;
        }

        public String getJawabanB() {
            return jawabanB;
        }

        public void setJawabanB(String jawabanB) {
            this.jawabanB = jawabanB;
        }

        public String getJawabanC() {
            return jawabanC;
        }

        public void setJawabanC(String jawabanC) {
            this.jawabanC = jawabanC;
        }

        public String getJawabanD() {
            return jawabanD;
        }

        public void setJawabanD(String jawabanD) {
            this.jawabanD = jawabanD;
        }

        String jawabanA;
        String jawabanB;
        String jawabanC;
        String jawabanD;

    }
}
