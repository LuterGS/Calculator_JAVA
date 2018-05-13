import java.util.ArrayList;

public class Model {

    private ArrayList<String> save = new ArrayList<String>();

    String number_input = "";
    String operator = "";

    public Model(){

    }

    private int detector(String input){

        System.out.printf("DETECTOR START, input is %s\n", input);
        //1~5 까지는 한번에 연산 가능한 연산자
        if(input.equals("sqrt")){ return 1;}
        if(input.equals("x^2")){ return 2;}
        if(input.equals("x^3")){ return 3;}
        if(input.equals("1/x")){ return 4;}
        if(input.equals("+/-")){ return 5;}

        //6~10 까지는 한번에 연산 불가능한 연산자
        if(input.equals("%")){ return 6;}
        if(input.equals("/")){ return 7;}
        if(input.equals("*")){ return 8;}
        if(input.equals("-")){ return 9;}
        if(input.equals("+")){ return 10;}

        //11은 = 연산자
        if(input.equals("=")){ return 11;}

        //12는 숫자, 13은 백스페이스
        if(input.equals("0")){ return 12; }
        if(input.equals("1")){ return 12; }
        if(input.equals("2")){ return 12; }
        if(input.equals("3")){ return 12; }
        if(input.equals("4")){ return 12; }
        if(input.equals("5")){ return 12; }
        if(input.equals("6")){ return 12; }
        if(input.equals("7")){ return 12; }
        if(input.equals("8")){ return 12; }
        if(input.equals("9")){ return 12; }
        if(input.equals(".")){ return 12; }
        if(input.equals("DEL")){ return 13; }

        //초기화는 14
        if(input.equals("C")){ return 14; }
        if(input.equals("CE")){ return 14; }
        return 0;
    }

    public int get_signal(String input) {


        //숫자면 숫자 입력
        //연산자일 때 if문 분기
        // 만약 다른 수를 받지 않아도 되는 연산자일때
        //계산, 초기화
        // 다른 수를 받아야 하는 연산자일 때
        //숫자 입력
        // = 입력했을 때 연산
        // 나머지 입력했을 때는 연산자 교체

        int detect = detector(input);
        System.out.printf("Detect Finish, detect : %d\n", detect);
        String temp;

        if (detect == 12 || detect == 13) {
            //숫자나 백스페이스 입력받았을때
            if (detect == 13) {
                //백스페이스일때
                if (number_input.length() == 1) {
                    number_input = "";
                } else {
                    number_input = number_input.substring(0, number_input.length() - 1);
                }
            } else {
                number_input = number_input + input;
                System.out.printf("TEST number input, %s\n", number_input);
            }
            //숫자일때
            return 1;

        } else {
            //연산자일때

            save.add(save.size(), number_input);
            number_input = "";

            if (detect >= 1 && detect <= 5) {

                operator = input;
                temp = calculate_case1(save.get(save.size() - 1), operator);
                save.add(save.size(), temp);
                number_input = temp;
                //calculate 한 값을 number_input에다가도 넣고, save.add 시킴
                //출력은 save의 마지막에서 두 번째 값과 연산자가 sub
                //save.add의 마지막이 main

                //이런 형태의 return을 2로 잡음
                return 2;

            }
            else if(detect >= 6 && detect <= 10){
                //뒤 자리에 수가 와야하는 연산자일 때
                operator = input;
            }else if(detect == 11){

                if(operator.equals("")){
                    //만약 그냥 = 를 눌렀을 땐 아무것도 하지 않음
                    return 0;
                }else{
                    temp = calculate_case2(save.get(save.size() - 2), save.get(save.size() - 1), operator);
                    save.add(save.size(), temp);
                    number_input = temp;
                    return 3;
                }
                //연산처리, operator와, save의 마지막에서 두 번째 값과, 첫 번째 값을 연산
                //save 마지막두번째 - operator - save 마지막
                //출력값을 save의 마지막에 저장, number_input에도 저장
                //출력은 sub가 save의 마지막 3, 2번째와 operator
                //main은 save의 마지막 첫번째
                //이럴 때 return은 3
            }else if(detect == 14){
                //초기화
                save.add(save.size(), "0");
                operator = "";
                number_input = "";
                //이럴 때 return은 4
                return 4;
            }
        }

        return 0;
    }


    private String calculate_case1(String number, String operator){

        double num = Double.parseDouble(number);

        System.out.printf("%s , operator %s\n", number, operator);
        if(operator.equals("sqrt")){
            num = Math.sqrt(num);
        }else if(operator.equals("x^2")){
            num = Math.pow(num, 2);
        }else if(operator.equals("x^3")){
            num = Math.pow(num, 3);
        }else if(operator.equals("1/x")){
            num = 1 / num;
        }else if(operator.equals("+/-")){
            num = -1 * num;
        }

        return String.valueOf(num);
    }

    private String calculate_case2(String number1, String number2, String operator){

        double num1 = Double.parseDouble(number1);
        double num2 = Double.parseDouble(number2);
        double num3 = 0;

        System.out.printf("%s , %s, operator %s\n", number1, number2, operator);

        if(operator.equals("%")){
            num3 = num1 % num2;
        }else if(operator.equals("/")){
            num3 = num1 / num2;
        }else if(operator.equals("*")){
            num3 = num1 * num2;
        }else if (operator.equals("-")){
            num3 = num1 - num2;
        }else if(operator.equals("+")){
            num3 = num1 + num2;
        }

        return String.valueOf(num3);
    }

    public String getNumber_input(){
        return number_input;
    }

    public String getOperator(){
        String temp = operator;
        operator = "";
        return temp;
    }

    public String getSave_last(int num){

        return save.get(save.size() - num);
    }
}
