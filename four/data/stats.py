import math

L = 0.000208
B = 800.0/3.36e-5

def T( n ):
  return (n**2)*(1.5599345433528e-05)

def N( n, K ):
  return (L + (1/B)*((n**2)*64)) * math.ceil( math.log( K, 2 ) )

def runtime( n, K ):
  return T(n)/K + N(n,K)

def speedup( seq, par ):
  return seq/par

def eff( speed, k ):
  return speed/k 

def esdf( seq, par, k ):
  return (k*par-seq)/(k*seq-seq)

seqen = T( 1260 )

o = runtime( 1260, 1 )
t = runtime( 1260, 2 )
f = runtime( 1260, 4 )
e = runtime( 1260, 8 )
tw = runtime( 1260, 20 )
fo = runtime( 1260, 40 )

print seqen
print o
print t
print f
print e
print tw
print fo

print " #1 %s %s "%(speedup(seqen,o),eff(speedup(seqen,o),1)) 
print " #2 %s %s %s "%(speedup(seqen,t),eff(speedup(seqen,t),2),esdf(seqen,t,2)) 
print " #4 %s %s %s "%(speedup(seqen,f),eff(speedup(seqen,f),4),esdf(seqen,f,4)) 
print " #8 %s %s %s "%(speedup(seqen,e),eff(speedup(seqen,e),8),esdf(seqen,e,8)) 
print "#20 %s %s %s "%(speedup(seqen,tw),eff(speedup(seqen,tw),20),esdf(seqen,tw,20)) 
print "#40 %s %s %s "%(speedup(seqen,fo),eff(speedup(seqen,fo),40),esdf(seqen,fo,40)) 

seqen = T( 4000 )
o = runtime( 4000, 1 )
t = runtime( 4000, 2 )
f = runtime( 4000, 4 )
e = runtime( 4000, 8 )
tw = runtime( 4000, 20 )
fo = runtime( 4000, 40 )

print seqen
print o
print t
print f
print e
print tw
print fo

print " #1 %s %s "%(speedup(seqen,o),eff(speedup(seqen,o),1)) 
print " #2 %s %s %s "%(speedup(seqen,t),eff(speedup(seqen,t),2),esdf(seqen,t,2)) 
print " #4 %s %s %s "%(speedup(seqen,f),eff(speedup(seqen,f),4),esdf(seqen,f,4)) 
print " #8 %s %s %s "%(speedup(seqen,e),eff(speedup(seqen,e),8),esdf(seqen,e,8)) 
print "#20 %s %s %s "%(speedup(seqen,tw),eff(speedup(seqen,tw),20),esdf(seqen,tw,20)) 
print "#40 %s %s %s "%(speedup(seqen,fo),eff(speedup(seqen,fo),40),esdf(seqen,fo,40)) 
