
def speedup( seq, par ):
  return seq/par

def eff( speed, k ):
  return speed/k 

def esdf( seq, par, k ):
  return (k*par-seq)/(k*seq-seq)

seqen = 48963

onef   = 49096.0 
twof   = 48676.0
fourf  = 48039.0 
eightf = 47603.0 

oned   = 49139.0 
twod   = 26087.0 
fourd  = 14197.0 
eightd =  8571.0 

onedt   =49061.0 
twodt   =32776.0 
fourdt  =25548.0 
eightdt =25320.0 

oneg   = 49034.0 
twog   = 26124.0 
fourg  = 14297.0 
eightg =  8386.0 

onegt   =  48979.0
twogt   =  29006.0
fourgt  =  22625.0
eightgt =  20104.0


print "#1f %s %s " % ( speedup( seqen, onef ), eff( speedup( seqen, onef ), 1 ) )
print "#2f %s %s " % ( speedup( seqen, twof ), eff( speedup( seqen, twof ), 2 ) )
print "#4f %s %s " % ( speedup( seqen, fourf ), eff( speedup( seqen, fourf ), 4 ) )
print "#8f %s %s " % ( speedup( seqen, eightf ), eff( speedup( seqen, eightf ), 8 ) )
print ""
print "#1d %s %s " % ( speedup( seqen, oned ), eff( speedup( seqen, oned ), 1 ) )
print "#2d %s %s " % ( speedup( seqen, twod ), eff( speedup( seqen, twod ), 2 ) )
print "#4d %s %s " % ( speedup( seqen, fourd ), eff( speedup( seqen, fourd ), 4 ) )
print "#8d %s %s " % ( speedup( seqen, eightd ), eff( speedup( seqen, eightd ), 8 ) )
print ""
print "#1dt %s %s " % ( speedup( seqen, onedt ), eff( speedup( seqen, onedt ), 1 ) )
print "#2dt %s %s " % ( speedup( seqen, twodt ), eff( speedup( seqen, twodt ), 2 ) )
print "#4dt %s %s " % ( speedup( seqen, fourdt ), eff( speedup( seqen, fourdt ), 4 ) )
print "#8dt %s %s " % ( speedup( seqen, eightdt ), eff( speedup( seqen, eightdt ), 8 ) )
print ""
print "#1g %s %s " % ( speedup( seqen, oneg ), eff( speedup( seqen, oneg ), 1 ) )
print "#2g %s %s " % ( speedup( seqen, twog ), eff( speedup( seqen, twog ), 2 ) )
print "#4g %s %s " % ( speedup( seqen, fourg ), eff( speedup( seqen, fourg ), 4 ) )
print "#8g %s %s " % ( speedup( seqen, eightg ), eff( speedup( seqen, eightg ), 8 ) )
print ""
print "#1gt %s %s " % ( speedup( seqen, onegt ), eff( speedup( seqen, onegt ), 1 ) )
print "#2gt %s %s " % ( speedup( seqen, twogt ), eff( speedup( seqen, twogt ), 2 ) )
print "#4gt %s %s " % ( speedup( seqen, fourgt ), eff( speedup( seqen, fourgt ), 4 ) )
print "#8gt %s %s " % ( speedup( seqen, eightgt ), eff( speedup( seqen, eightgt ), 8 ) )

seqen = 46934

onef   = 47366.0 
twof   = 46232.0
fourf  = 48701.0 
eightf = 47315.0 

oned   = 47059.0 
twod   = 24783.0 
fourd  = 13316.0 
eightd =  7751.0 

onedt   =47087.0 
twodt   =24323.0 
fourdt  =23524.0 
eightdt =24323.0 

oneg   = 47233.0 
twog   = 46889.0 
fourg  = 45252.0 
eightg = 39478.0 

onegt   =46988.0
twogt   =45706.0
fourgt  =45417.0
eightgt =39443.0


print "#1f %s %s " % ( speedup( seqen, onef ), eff( speedup( seqen, onef ), 1 ) )
print "#2f %s %s " % ( speedup( seqen, twof ), eff( speedup( seqen, twof ), 2 ) )
print "#4f %s %s " % ( speedup( seqen, fourf ), eff( speedup( seqen, fourf ), 4 ) )
print "#8f %s %s " % ( speedup( seqen, eightf ), eff( speedup( seqen, eightf ), 8 ) )
print ""
print "#1d %s %s " % ( speedup( seqen, oned ), eff( speedup( seqen, oned ), 1 ) )
print "#2d %s %s " % ( speedup( seqen, twod ), eff( speedup( seqen, twod ), 2 ) )
print "#4d %s %s " % ( speedup( seqen, fourd ), eff( speedup( seqen, fourd ), 4 ) )
print "#8d %s %s " % ( speedup( seqen, eightd ), eff( speedup( seqen, eightd ), 8 ) )
print ""
print "#1dt %s %s " % ( speedup( seqen, onedt ), eff( speedup( seqen, onedt ), 1 ) )
print "#2dt %s %s " % ( speedup( seqen, twodt ), eff( speedup( seqen, twodt ), 2 ) )
print "#4dt %s %s " % ( speedup( seqen, fourdt ), eff( speedup( seqen, fourdt ), 4 ) )
print "#8dt %s %s " % ( speedup( seqen, eightdt ), eff( speedup( seqen, eightdt ), 8 ) )
print ""
print "#1g %s %s " % ( speedup( seqen, oneg ), eff( speedup( seqen, oneg ), 1 ) )
print "#2g %s %s " % ( speedup( seqen, twog ), eff( speedup( seqen, twog ), 2 ) )
print "#4g %s %s " % ( speedup( seqen, fourg ), eff( speedup( seqen, fourg ), 4 ) )
print "#8g %s %s " % ( speedup( seqen, eightg ), eff( speedup( seqen, eightg ), 8 ) )
print ""
print "#1gt %s %s " % ( speedup( seqen, onegt ), eff( speedup( seqen, onegt ), 1 ) )
print "#2gt %s %s " % ( speedup( seqen, twogt ), eff( speedup( seqen, twogt ), 2 ) )
print "#4gt %s %s " % ( speedup( seqen, fourgt ), eff( speedup( seqen, fourgt ), 4 ) )
print "#8gt %s %s " % ( speedup( seqen, eightgt ), eff( speedup( seqen, eightgt ), 8 ) )


