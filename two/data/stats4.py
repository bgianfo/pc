
def speedup( seq, par ):
  return seq/par

def eff( speed, k ):
  return speed/k 

def esdf( seq, par, k ):
  return (k*par-seq)/(k*seq-seq)

seqen = 33458.0

o = 34668.0
t = 96899.0
f = 10955.0
e =  8101.0 

print "No Overlap"
print "#1 %s %s "%(speedup(seqen,o),eff(speedup(seqen,o),1)) 
print "#2 %s %s %s "%(speedup(seqen,t),eff(speedup(seqen,t),2),esdf(seqen,t,2)) 
print "#4 %s %s %s "%(speedup(seqen,f),eff(speedup(seqen,f),4),esdf(seqen,f,4)) 
print "#8 %s %s %s "%(speedup(seqen,e),eff(speedup(seqen,e),8),esdf(seqen,e,8)) 

o = 34813.0   
t = 18965.0
f = 10886.0
e = 18385.0 

print "Overlap"
print "#1 %s %s "%(speedup(seqen,o),eff(speedup(seqen,o),1)) 
print "#2 %s %s %s "%(speedup(seqen,t),eff(speedup(seqen,t),2),esdf(seqen,t,2)) 
print "#4 %s %s %s "%(speedup(seqen,f),eff(speedup(seqen,f),4),esdf(seqen,f,4)) 
print "#8 %s %s %s "%(speedup(seqen,e),eff(speedup(seqen,e),8),esdf(seqen,e,8)) 
