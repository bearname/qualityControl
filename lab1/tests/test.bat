REM Normal triangle
cd ../out
java -jar lab1.jar 3 4 5 > out.txt
cd ../tests
fc "..\out\out.txt" normal.txt
IF ERRORLEVEL 1 GOTO err

REM Isosceles triangle
cd ../out
java -jar lab1.jar 6 3 6  > out.txt
cd ../tests
fc "..\out\out.txt" isosceles.txt
IF ERRORLEVEL 1 GOTO err

REM Equilateral triangle
cd ../out
java -jar lab1.jar 4 4 4  > out.txt
cd ../tests
fc "..\out\out.txt" equilateral.txt
IF ERRORLEVEL 1 GOTO err

REM check work with big number
cd ../out
java -jar lab1.jar 12342354142341242134234213423431241234235414234124213423421342343124123423541423412421342342134 12342354142341242134234213423431241234235414234124213423421342343124123423541423412421342342134 5 > out.txt
cd ../tests
fc "..\out\out.txt" isosceles.txt
IF ERRORLEVEL 1 GOTO err

REM Not triangle
cd ../out
java -jar lab1.jar 1 2 3 > out.txt
cd ../tests
fc "..\out\out.txt" not-triangle.txt
IF ERRORLEVEL 1 GOTO err

REM Negative number
cd ../out
java -jar lab1.jar 4 -2 2  > out.txt
cd ../tests
fc "..\out\out.txt" negative-number.txt
IF ERRORLEVEL 1 GOTO err

REM  Invalid number format
cd ../out
java -jar lab1.jar 2.2.  2 3 > out.txt
cd ../tests
fc "..\out\out.txt" invalid-number-format.txt
IF ERRORLEVEL 1 GOTO err

REM literal character
cd ../out
java -jar lab1.jar 2 qwer qwer > out.txt
cd ../tests
fc "..\out\out.txt" invalid-number-format.txt
IF ERRORLEVEL 1 GOTO err

REM invalid argument count
cd ../out
java -jar lab1.jar > out.txt
cd ../tests
fc "..\out\out.txt" invalid-argument-count.txt
IF ERRORLEVEL 1 GOTO err

GOTO success

:err
ECHO Program testing failed :-(
EXIT 1

:success
echo Program testing succeeded :-)
EXIT 0
