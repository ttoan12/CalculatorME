package com.ltdd.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class CalculatorScientificFragment extends Fragment {

    private Button btnNum0, btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9, btnPlus, btnMinus, btnDivide, btnMultiply, btnEqual, btnReverse, btnDel, btnClear, btnDot, btnPercent, btnBraceOpen, btnBraceClose, btnSin, btnCos, btnTan, btnMod, btnPow, btnRoot, btnPi, btnFactorial;
    private TextView txtResult, txtHistory;
    private boolean clearText, clearHistory, isBraced;
    private List<String> actionList;
    private int braceOpened;

    public CalculatorScientificFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator_scientific, container, false);

        Mapping(view);
        CreateBtnFunction();

        return view;
    }

    private void Mapping(View view) {
        btnNum0 = view.findViewById(R.id.btnNum0);
        btnNum1 = view.findViewById(R.id.btnNum1);
        btnNum2 = view.findViewById(R.id.btnNum2);
        btnNum3 = view.findViewById(R.id.btnNum3);
        btnNum4 = view.findViewById(R.id.btnNum4);
        btnNum5 = view.findViewById(R.id.btnNum5);
        btnNum6 = view.findViewById(R.id.btnNum6);
        btnNum7 = view.findViewById(R.id.btnNum7);
        btnNum8 = view.findViewById(R.id.btnNum8);
        btnNum9 = view.findViewById(R.id.btnNum9);
        btnPlus = view.findViewById(R.id.btnPlus);
        btnMinus = view.findViewById(R.id.btnMinus);
        btnDivide = view.findViewById(R.id.btnDivide);
        btnMultiply = view.findViewById(R.id.btnMultiply);
        btnPercent = view.findViewById(R.id.btnPercent);
        btnEqual = view.findViewById(R.id.btnEqual);
        btnClear = view.findViewById(R.id.btnClear);
        btnDel = view.findViewById(R.id.btnDel);
        btnDot = view.findViewById(R.id.btnDot);
        btnBraceOpen = view.findViewById(R.id.btnBracketOpen);
        btnBraceClose = view.findViewById(R.id.btnBracketClose);
        btnSin = view.findViewById(R.id.btnSin);
        btnCos = view.findViewById(R.id.btnCos);
        btnTan = view.findViewById(R.id.btnTan);
        btnMod = view.findViewById(R.id.btnMod);
        btnPow = view.findViewById(R.id.btnPow);
        btnRoot = view.findViewById(R.id.btnRoot);
        btnPi = view.findViewById(R.id.btnPi);
        btnFactorial = view.findViewById(R.id.btnFactorial);
        btnReverse = view.findViewById(R.id.btnReverse);
        txtHistory = view.findViewById(R.id.txtHistory);
        txtResult = view.findViewById(R.id.txtResult);
        clearText = clearHistory = true;
        isBraced = false;
        braceOpened = 0;
        actionList = new ArrayList<>();
    }

    private void CreateBtnFunction() {
        btnNum0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("0");
            }
        });
        btnNum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("1");
            }
        });
        btnNum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("2");
            }
        });
        btnNum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("3");
            }
        });
        btnNum4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("4");
            }
        });
        btnNum5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("5");
            }
        });
        btnNum6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("6");
            }
        });
        btnNum7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("7");
            }
        });
        btnNum8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("8");
            }
        });
        btnNum9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumBtnPressed("9");
            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clearText) {
                    txtResult.setText("");
                    clearText = false;
                }
                if (txtResult.getText().toString().equals("")) {
                    txtResult.append("0.");
                } else {
                    txtResult.append(".");
                }
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBraced) {
                    actionList.add("+");
                    String historyTextAppend = " + ";
                    txtHistory.append(historyTextAppend);
                    clearText = true;
                    isBraced = false;
                    return;
                }
                if (IsNull() || IsEditSymbol("+", "+")) return;
                FixDot();
                String resultText = txtResult.getText().toString();
                actionList.add(resultText);
                actionList.add("+");
                String historyTextAppend = (resultText.toCharArray()[0] == '-' ? "(" + resultText + ")" : resultText) + " + ";
                txtHistory.append(historyTextAppend);
                clearText = true;
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBraced) {
                    actionList.add("-");
                    String historyTextAppend = " - ";
                    txtHistory.append(historyTextAppend);
                    clearText = true;
                    isBraced = false;
                    return;
                }
                if (IsNull() || IsEditSymbol("-", "-")) return;
                FixDot();
                String resultText = txtResult.getText().toString();
                actionList.add(resultText);
                actionList.add("-");
                String historyTextAppend = (resultText.toCharArray()[0] == '-' ? "(" + resultText + ")" : resultText) + " - ";
                txtHistory.append(historyTextAppend);
                clearText = true;
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBraced) {
                    actionList.add("*");
                    String historyTextAppend = " × ";
                    txtHistory.append(historyTextAppend);
                    clearText = true;
                    isBraced = false;
                    return;
                }
                if (IsNull() || IsEditSymbol("*", "×")) return;
                FixDot();
                String resultText = txtResult.getText().toString();
                actionList.add(resultText);
                actionList.add("*");
                String historyTextAppend = (resultText.toCharArray()[0] == '-' ? "(" + resultText + ")" : resultText) + " × ";
                txtHistory.append(historyTextAppend);
                clearText = true;
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBraced) {
                    actionList.add("/");
                    String historyTextAppend = " ÷ ";
                    txtHistory.append(historyTextAppend);
                    clearText = true;
                    isBraced = false;
                    return;
                }
                if (IsNull() || IsEditSymbol("/", "÷")) return;
                FixDot();
                String resultText = txtResult.getText().toString();
                actionList.add(resultText);
                actionList.add("/");
                String historyTextAppend = (resultText.toCharArray()[0] == '-' ? "(" + resultText + ")" : resultText) + " ÷ ";
                txtHistory.append(historyTextAppend);
                clearText = true;
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsNull()) return;
                if (clearHistory) {
                    txtHistory.setText("");
                    clearHistory = false;
                }
                FixDot();
                if (isBraced) {
                    while (braceOpened > 0) {
                        actionList.add(")");
                        txtHistory.append(")");
                        braceOpened -= 1;
                    }
                    double result = Calculate(actionList);
                    if (result == -1) {
                        return;
                    }
                    actionList.clear();
                    String historyTextAppend = " = " + (result == (int) result ? Integer.toString((int) result) : result);
                    txtHistory.append(historyTextAppend);
                    String displayText = (result == (int) result ? Integer.toString((int) result) : result) + "";
                    txtResult.setText(displayText);
                    clearText = true;
                    clearHistory = true;
                    isBraced = false;
                    return;
                }
                String resultText = txtResult.getText().toString();
                actionList.add(resultText);
                txtHistory.append(resultText);
                while (braceOpened > 0) {
                    actionList.add(")");
                    txtHistory.append(")");
                    braceOpened -= 1;
                }
                double result = Calculate(actionList);
                if (result == -1) {
                    return;
                }
                actionList.clear();
                String historyTextAppend = " = " + (result == (int) result ? Integer.toString((int) result) : result);
                txtHistory.append(historyTextAppend);
                String displayText = (result == (int) result ? Integer.toString((int) result) : result) + "";
                txtResult.setText(displayText);
                clearText = true;
                clearHistory = true;
            }
        });

        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int l = actionList.size();
                if (l > 0) {
                    double num = Double.parseDouble(actionList.get(l - 2));
                    double percent = Double.parseDouble(txtResult.getText().toString());
                    double result = num / 100 * percent;
                    txtResult.setText(result == (int) result ? Integer.toString((int) result) : result + "");
                }
            }
        });

        btnReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = txtResult.getText().toString();
                if (text.toCharArray()[0] == '-')
                    txtResult.setText(text.substring(1));
                else {
                    String displayText = "-" + text;
                    txtResult.setText(displayText);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = txtResult.getText().toString();
                if (text.length() > 0)
                    txtResult.setText(text.substring(0, text.length() - 1));
                else
                    clearText = true;
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtResult.setText("");
                txtHistory.setText("");
                clearText = true;
                clearHistory = true;
            }
        });

        btnBraceOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clearText && clearHistory) {
                    actionList.add("(");
                    txtHistory.setText("(");
                    clearHistory = false;
                    braceOpened += 1;
                } else if (clearText) {
                    actionList.add("(");
                    txtHistory.append("(");
                    braceOpened += 1;
                }
            }
        });

        btnBraceClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = txtHistory.getText().toString();
                if (braceOpened > 0 && text.toCharArray()[text.length() - 1] == '(') {
                    actionList.add("0");
                    actionList.add(")");
                    txtHistory.append("0)");
                    braceOpened -= 1;
                    isBraced = true;
                } else if (braceOpened > 0) {
                    String resultText = txtResult.getText().toString();
                    actionList.add(resultText);
                    actionList.add(")");
                    txtHistory.append(resultText + ")");
                    braceOpened -= 1;
                    isBraced = true;
                }
            }
        });
    }

    private double Calculate(List<String> actionList) {
        if (actionList.size() < 1) return -1;
        while (true) {
            // Nếu có ngoặc
            if (actionList.contains("(")) {
                int indexBraceOpen = actionList.indexOf("(");
                int indexBraceClose = -1;

                while (indexBraceClose == -1) {
                    for (int i = indexBraceOpen + 1; i < actionList.size(); i++) {
                        if (actionList.get(i).equals(")")) {
                            indexBraceClose = i;
                            List<String> tempActionList = actionList.subList(indexBraceOpen + 1, indexBraceClose);
                            Calculate(tempActionList);
                            actionList.remove(indexBraceOpen + 2);
                            actionList.remove(indexBraceOpen);
                            break;
                        } else if (actionList.get(i).equals("(")) {
                            indexBraceOpen = i;
                            break;
                        }
                    }
                }
            }
            // Nếu nhân hoặc chia
            else if (actionList.contains("/") || actionList.contains("*")) {
                int indexMultiply = actionList.indexOf("*");
                int indexDivide = actionList.indexOf("/");

                // Nếu có cả nhân và chia -> Tính từ trái qua phải
                if (indexMultiply > -1 && indexDivide > -1) {
                    if (indexMultiply <= indexDivide) {
                        double numA = Double.parseDouble(actionList.get(indexMultiply - 1));
                        double numB = Double.parseDouble(actionList.get(indexMultiply + 1));
                        double result = numA * numB;
                        actionList.set(indexMultiply - 1, result + "");
                        actionList.remove(indexMultiply + 1);
                        actionList.remove(indexMultiply);
                    } else {
                        double numA = Double.parseDouble(actionList.get(indexDivide - 1));
                        double numB = Double.parseDouble(actionList.get(indexDivide + 1));
                        double result = numA / numB;
                        actionList.set(indexDivide - 1, result + "");
                        actionList.remove(indexDivide + 1);
                        actionList.remove(indexDivide);
                    }
                    // Nếu chỉ có nhân
                } else if (indexMultiply > -1) {
                    double numA = Double.parseDouble(actionList.get(indexMultiply - 1));
                    double numB = Double.parseDouble(actionList.get(indexMultiply + 1));
                    double result = numA * numB;
                    actionList.set(indexMultiply - 1, result + "");
                    actionList.remove(indexMultiply + 1);
                    actionList.remove(indexMultiply);
                    // Nếu chỉ có chia
                } else if (indexDivide > -1) {
                    double numA = Double.parseDouble(actionList.get(indexDivide - 1));
                    double numB = Double.parseDouble(actionList.get(indexDivide + 1));
                    double result = numA / numB;
                    actionList.set(indexDivide - 1, result + "");
                    actionList.remove(indexDivide + 1);
                    actionList.remove(indexDivide);
                }
            }
            // Nếu cộng trừ
            else if (actionList.contains("+") || actionList.contains("-")) {
                int indexPlus = actionList.indexOf("+");
                int indexMinus = actionList.indexOf("-");

                // Nếu có cả cộng và trừ -> Tính từ trái qua phải
                if (indexPlus > -1 && indexMinus > -1) {
                    if (indexPlus <= indexMinus) {
                        double numA = Double.parseDouble(actionList.get(indexPlus - 1));
                        double numB = Double.parseDouble(actionList.get(indexPlus + 1));
                        double result = numA + numB;
                        actionList.set(indexPlus - 1, result + "");
                        actionList.remove(indexPlus + 1);
                        actionList.remove(indexPlus);
                    } else {
                        double numA = Double.parseDouble(actionList.get(indexMinus - 1));
                        double numB = Double.parseDouble(actionList.get(indexMinus + 1));
                        double result = numA - numB;
                        actionList.set(indexMinus - 1, result + "");
                        actionList.remove(indexMinus + 1);
                        actionList.remove(indexMinus);
                    }
                    // Nếu chỉ có cộng
                } else if (indexPlus > -1) {
                    double numA = Double.parseDouble(actionList.get(indexPlus - 1));
                    double numB = Double.parseDouble(actionList.get(indexPlus + 1));
                    double result = numA + numB;
                    actionList.set(indexPlus - 1, result + "");
                    actionList.remove(indexPlus + 1);
                    actionList.remove(indexPlus);
                    // Nếu chỉ có trừ
                } else if (indexMinus > -1) {
                    double numA = Double.parseDouble(actionList.get(indexMinus - 1));
                    double numB = Double.parseDouble(actionList.get(indexMinus + 1));
                    double result = numA - numB;
                    actionList.set(indexMinus - 1, result + "");
                    actionList.remove(indexMinus + 1);
                    actionList.remove(indexMinus);
                }
            } else break;
        }

        return Double.parseDouble(actionList.get(0));
    }

    private void FixDot() {
        String resultText = txtResult.getText().toString();
        if (resultText.substring(resultText.length() - 1).equals(".")) {
            txtResult.append("0");
        }
    }

    private boolean IsEditSymbol(String symbol, String displayedSymbol) {
        if (clearHistory) {
            txtHistory.setText("");
            clearHistory = false;
        } else if (clearText && actionList.size() > 1) {
            actionList.set(actionList.size() - 1, symbol);
            String text = txtHistory.getText().toString();
            String displayText = text.substring(0, text.length() - 3) + " " + displayedSymbol + " ";
            txtHistory.setText(displayText);
            return true;
        }
        return false;
    }

    private void NumBtnPressed(String num) {
        if (clearText) {
            txtResult.setText("");
            clearText = false;
        }
        txtResult.append(num);
    }

    private boolean IsNull() {
        return txtResult.getText().toString().length() <= 0;
    }
}
