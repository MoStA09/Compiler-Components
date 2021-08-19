grammar task10;

s  :l {System.out.println($l.val2);} ;
l  returns [int val1 , int i = -1,int val2 ] :b l {
 $i = $l.i + 1; $val2 = $l.val2+ ($l.val1 + $b.val) * (int)Math.pow(2.0,$i) ;
}
|  ;
b returns [int val]:  B {$val = Integer.parseInt($B.text);};
B : '0' | '1';
//=  {$inh + 2*$val} |  {$val = $inh}
//$val1 =  (Math.pow(2,$b.i)*$b.val)+$val1;
//System.out.println($l.val1 + $b.val * Math.pow(2,$i));