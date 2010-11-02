#!/bin/bash/

rm generated/ -r
mkdir generated/
cd generated/
pdflatex ../DPP.tex
xpdf DPP.pdf
