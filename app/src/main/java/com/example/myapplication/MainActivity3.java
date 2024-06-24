package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity3 extends AppCompatActivity {

    private Button throwButton;
    private Button scoreButton;

    private ImageView[] humanDiceImages;
    private ImageView[] computerDiceImages;

    private TextView compScore;
    private TextView humanScore;
    private TextView winLoseCount;

    private List<Integer> hDiceValues = new ArrayList<>();
    private List<Integer> cDiceValues = new ArrayList<>();

    private List<Integer> reRollValuesH = new ArrayList<>();
    private List<Integer> reRollValuesC = new ArrayList<>();

    private int rollCount = 0;

    private int targett = 0;

    private boolean hard = true;

    private int totalSumOfC = 0;
    private int totalSumOfH = 0;

    private int sumOfC = 0;
    private int sumOfH = 0;

    private int winCount = 0;
    private int loseCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        throwButton = findViewById(R.id.rollButton);
        scoreButton = findViewById(R.id.scoreButton);
        compScore = findViewById(R.id.compScore);
        humanScore = findViewById(R.id.humanScore);
        winLoseCount = findViewById(R.id.winLoseCount);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            targett = bundle.getInt("target");
            hard = bundle.getBoolean("hardMode");
        }

        humanDiceImages = new ImageView[] {
                findViewById(R.id.hDice1),
                findViewById(R.id.hDice2),
                findViewById(R.id.hDice3),
                findViewById(R.id.hDice4),
                findViewById(R.id.hDice5)
        };

        computerDiceImages = new ImageView[] {
                findViewById(R.id.cDice1),
                findViewById(R.id.cDice2),
                findViewById(R.id.cDice3),
                findViewById(R.id.cDice4),
                findViewById(R.id.cDice5)
        };

        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rollCount != 0) {
                    if (hard) {
                        computerStratergy();
                    } else {
                        randomStratergy();
                    }

                    for (int x : hDiceValues) {
                        sumOfH += x;
                    }
                    totalSumOfH += sumOfH;
                    humanScore.setText(String.valueOf(totalSumOfH));

                    for (int j : cDiceValues) {
                        sumOfC += j;
                    }
                    totalSumOfC += sumOfC;
                    compScore.setText(String.valueOf(totalSumOfC));

                    winnerSelect(totalSumOfC, totalSumOfH);

                    cDiceValues.clear();
                    hDiceValues.clear();
                    reRollValuesH.clear();
                    reRollValuesC.clear();
                    rollCount = 0;
                } else {
                    Toast.makeText(getApplicationContext(), "Click Throw button before Score", Toast.LENGTH_SHORT).show();
                }
            }
        });

        throwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hDiceValues.clear();
                sumOfC = 0;
                sumOfH = 0;

                for (int i = 0; i < 5; i++) {
                    if (!reRollValuesH.contains(i)) {
                        int randomIntH = new Random().nextInt(6) + 1;
                        int drawableResource;
                        switch (randomIntH) {
                            case 1:
                                drawableResource = R.drawable.dice_one;
                                break;
                            case 2:
                                drawableResource = R.drawable.dice_two;
                                break;
                            case 3:
                                drawableResource = R.drawable.dice_three;
                                break;
                            case 4:
                                drawableResource = R.drawable.dice_four;
                                break;
                            case 5:
                                drawableResource = R.drawable.dice_five;
                                break;
                            default:
                                drawableResource = R.drawable.dice_six;
                                break;
                        }
                        humanDiceImages[i].setImageResource(drawableResource);
                        humanDiceImages[i].setTag(randomIntH);
                        hDiceValues.add(randomIntH);
                    } else {
                        hDiceValues.add((Integer) humanDiceImages[i].getTag());
                    }
                }

                if (rollCount == 0) {
                    for (int i = 0; i < 5; i++) {
                        int randomIntC = new Random().nextInt(6) + 1;
                        int drawableResource;
                        switch (randomIntC) {
                            case 1:
                                drawableResource = R.drawable.dice_one1;
                                break;
                            case 2:
                                drawableResource = R.drawable.dice_two2;
                                break;
                            case 3:
                                drawableResource = R.drawable.dice_three3;
                                break;
                            case 4:
                                drawableResource = R.drawable.dice_four4;
                                break;
                            case 5:
                                drawableResource = R.drawable.dice_five5;
                                break;
                            default:
                                drawableResource = R.drawable.dice_six6;
                                break;
                        }
                        computerDiceImages[i].setImageResource(drawableResource);
                        cDiceValues.add(randomIntC);
                    }
                }
                rollCount++;

                if (hard) {
                    computerStratergy();
                } else {
                    randomStratergy();
                }

                if (rollCount == 3) {
                    for (int i : hDiceValues) {
                        sumOfH += i;
                    }
                    totalSumOfH += sumOfH;
                    humanScore.setText(String.valueOf(totalSumOfH));
                    for (int j : cDiceValues) {
                        sumOfC += j;
                    }
                    totalSumOfC += sumOfC;
                    compScore.setText(String.valueOf(totalSumOfC));

                    winnerSelect(totalSumOfC, totalSumOfH);
                    rollCount = 0;
                    reRollValuesH.clear();
                    cDiceValues.clear();
                }
            }
        });

        humanDiceImages[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rollCount != 0) {
                    reRollValuesH.add(0);
                }
            }
        });
        humanDiceImages[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rollCount != 0) {
                    reRollValuesH.add(1);
                }
            }
        });
        humanDiceImages[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rollCount != 0) {
                    reRollValuesH.add(2);
                }
            }
        });
        humanDiceImages[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rollCount != 0) {
                    reRollValuesH.add(3);
                }
            }
        });
        humanDiceImages[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rollCount != 0) {
                    reRollValuesH.add(4);
                }
            }
        });
    }

    private void winnerSelect(int finalSumOfC, int finalSumOfH) {
        if (finalSumOfC >= targett || finalSumOfH >= targett) {
            if (finalSumOfC > finalSumOfH) {
                winPopUp(finalSumOfC, finalSumOfH);
            } else if (finalSumOfH > finalSumOfC) {
                winPopUp(finalSumOfC, finalSumOfH);
            } else {
                winPopUp(sumOfC, sumOfH);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void winPopUp(int finalSumOfC, int finalSumOfH) {

        throwButton.setEnabled(false);
        scoreButton.setEnabled(false);

        @SuppressLint("InflateParams") View popUpView = getLayoutInflater().inflate(R.layout.win_lose_popup, null);
        PopupWindow popupWindow = new PopupWindow(
                popUpView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true
        );

        Button winOkButton = popUpView.findViewById(R.id.winOkButton);
        winOkButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

                throwButton.setEnabled(true);
                scoreButton.setEnabled(true);

                totalSumOfC = 0;
                totalSumOfH = 0;

                compScore.setText(String.valueOf(totalSumOfC));
                humanScore.setText(String.valueOf(totalSumOfH));

            }
        });

        TextView textWinLose = popUpView.findViewById(R.id.winPopUp);

        if (finalSumOfC > finalSumOfH) {
            textWinLose.setText("\n\n\nYou LOSE!");
            textWinLose.setTextColor(Color.RED);
            loseCount++;
        } else if (finalSumOfH > finalSumOfC) {
            textWinLose.setText("\n\n\nYOU WIN!");
            textWinLose.setTextColor(Color.GREEN);
            winCount++;
        }

        winLoseCount.setText("W: " + winCount + "/ L: " + loseCount);

        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("totalSumOfC", totalSumOfC);
        outState.putInt("totalSumOfH", totalSumOfH);
        outState.putInt("winCount", winCount);
        outState.putInt("loseCount", loseCount);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        totalSumOfC = savedInstanceState.getInt("totalSumOfC", 0);
        totalSumOfH = savedInstanceState.getInt("totalSumOfH", 0);
        winCount = savedInstanceState.getInt("winCount", 0);
        loseCount = savedInstanceState.getInt("loseCount", 0);

        compScore.setText(String.valueOf(totalSumOfC));
        humanScore.setText(String.valueOf(totalSumOfH));

        winLoseCount.setText("W: " + winCount + "/ L: " + loseCount);
    }

    private void randomStratergy() {
        for (int i = 0; i < 5; i++) {
            int randomIntC = new Random().nextInt(6) + 1;
            cDiceValues.set(i, randomIntC);
            int drawableResource;
            switch (randomIntC) {
                case 1:
                    drawableResource = R.drawable.dice_one1;
                    break;
                case 2:
                    drawableResource = R.drawable.dice_two2;
                    break;
                case 3:
                    drawableResource = R.drawable.dice_three3;
                    break;
                case 4:
                    drawableResource = R.drawable.dice_four4;
                    break;
                case 5:
                    drawableResource = R.drawable.dice_five5;
                    break;
                default:
                    drawableResource = R.drawable.dice_six6;
                    break;
            }
            computerDiceImages[i].setImageResource(drawableResource);
        }
    }

    private void computerStratergy() {
        int humanScoreSum = 0;
        for (int x : hDiceValues) {
            humanScoreSum += x;
        }
        for (int i = 0; i < 5; i++) {
            if (humanScoreSum >= totalSumOfC) {
                if (cDiceValues.get(i) < 5) {
                    int randomIntC = new Random().nextInt(6) + 1;
                    cDiceValues.set(i, randomIntC);
                    int drawableResource;
                    switch (randomIntC) {
                        case 1:
                            drawableResource = R.drawable.dice_one1;
                            break;
                        case 2:
                            drawableResource = R.drawable.dice_two2;
                            break;
                        case 3:
                            drawableResource = R.drawable.dice_three3;
                            break;
                        case 4:
                            drawableResource = R.drawable.dice_four4;
                            break;
                        case 5:
                            drawableResource = R.drawable.dice_five5;
                            break;
                        default:
                            drawableResource = R.drawable.dice_six6;
                            break;
                    }
                    computerDiceImages[i].setImageResource(drawableResource);
                }
            } else {
                if (cDiceValues.get(i) < 3) {
                    int randomIntC = new Random().nextInt(6) + 1;
                    cDiceValues.set(i, randomIntC);
                    int drawableResource;
                    switch (randomIntC) {
                        case 1:
                            drawableResource = R.drawable.dice_one1;
                            break;
                        case 2:
                            drawableResource = R.drawable.dice_two2;
                            break;
                        case 3:
                            drawableResource = R.drawable.dice_three3;
                            break;
                        case 4:
                            drawableResource = R.drawable.dice_four4;
                            break;
                        case 5:
                            drawableResource = R.drawable.dice_five5;
                            break;
                        default:
                            drawableResource = R.drawable.dice_six6;
                            break;
                    }
                    computerDiceImages[i].setImageResource(drawableResource);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
