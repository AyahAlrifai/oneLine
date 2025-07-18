## One Line Game Solution

- Enter number or rows and columns
- Enter the board where 
  - N: Not a node 
  - Y: Node 
  - S: Start Node


## Example 1

### Input
```
Y   N   Y   Y   Y   N
Y   Y   Y   S   Y   N
Y   Y   Y   Y   Y   Y
N   Y   Y   N   Y   Y
N   Y   Y   Y   Y   Y
N   N   N   Y   Y   Y
```
### The Result Is
```
27 	N 	21 	20 	19 	N 	
26 	23 	22 	1 	18 	N 	
25 	24 	3 	2 	17 	16 	
N 	5 	4 	N 	14 	15 	
N 	6 	7 	8 	13 	12 	
N 	N 	N 	9 	10 	11 	
```


## Example 2

### Input
```
N   N    Y   Y   Y   Y
N    Y   Y   Y   Y   Y
Y    Y   Y   Y   Y   Y
Y    Y   Y   S   Y   N
Y    Y   Y   Y   Y   N
N    Y   Y   N   N   N
Y    Y   N   N   N   N
```
### The Result Is
```
N 	N 	13 	14 	17 	18 	
N 	11 	12 	15 	16 	19 	
9 	10 	3 	2 	21 	20 	
8 	5 	4 	1 	22 	N 	
7 	6 	25 	24 	23 	N 	
N 	27 	26 	N 	N 	N 	
29 	28 	N 	N 	N 	N 	
```

## Example 3

### Input
```
Y   Y    Y   Y   S
Y   N    N   Y   Y
Y   N    N   N   Y
Y   N    N   N   Y
Y   N    N   N   Y
Y   Y    Y   Y   Y
```
### The Result Is
```
DFS Result:
15	16	17	18	1	
14	N	N	19	2	
13	N	N	N	3	
12	N	N	N	4	
11	N	N	N	5	
10	9	8	7	6	
BFS Result:
5	4	3	2	1	
6	N	N	19	18	
7	N	N	N	17	
8	N	N	N	16	
9	N	N	N	15	
10	11	12	13	14	
```