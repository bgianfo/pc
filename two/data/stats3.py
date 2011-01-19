
def speedup( seq, par ):
  return seq/par

def eff( speed, k ):
  return speed/k 

def esdf( seq, par, k ):
  return (k*par-seq)/(k*seq-seq)

seqen = 486774.0

o = 408043.0 
t = .0
f = .0 
e = .0 

print "#1 %s %s %s "%(speedup(seqen,o),eff(speedup(seqen,o),1),esdf(seqen,o,1)) 
print "#2 %s %s %s "%(speedup(seqen,t),eff(speedup(seqen,t),2),esdf(seqen,t,2)) 
print "#4 %s %s %s "%(speedup(seqen,f),eff(speedup(seqen,f),4),esdf(seqen,f,4)) 
print "#8 %s %s %s "%(speedup(seqen,e),eff(speedup(seqen,e),8),esdf(seqen,e,8)) 
