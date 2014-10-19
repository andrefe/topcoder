#!/usr/bin/python
import random
import sys


print "Usage: genInput.py NUM_OPERATIONS CHOSEN_INT_PERCENTAGE"

iterations = int(sys.argv[1])
balance = 0
operations = list()
chosenOperations = list()

for i in range(0,iterations):

	genOperation = random.randint(100,100000)
	shouldITakeIt = random.randint(0,100)
	if shouldITakeIt <= int(sys.argv[2]):
		balance = balance + genOperation
		chosenOperations.append(genOperation)

	operations.append(genOperation)

chosenOperations.sort(reverse=True)


print "input.txt " + str(len(operations))
print "------------"
print float(balance)/float(100)
for i in range(0,len(operations)):
	print float(operations[i])/float(100)
