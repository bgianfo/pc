
def speedup( seq, par ):
  return seq/par

def eff( speed, k ):
  return speed/k 

def esdf( seq, par, k ):
  return (k*par-seq)/(k*seq-seq)

seqen = 486774.0
od = 408043.0
td = 210629.0
fd = 131618.0
ed = 66904.0

print "test"
print "#1 %s %s " % ( speedup(seqen,od), eff(speedup(seqen,od),1) ) 
print "#2 %s %s %s" % ( speedup(seqen,td), eff(speedup(seqen,td),2), esdf(seqen,td,2) ) 
print "#4 %s %s %s" % ( speedup(seqen,fd), eff(speedup(seqen,fd),4), esdf(seqen,fd,4) ) 
print "#8 %s %s %s" % ( speedup(seqen,ed), eff(speedup(seqen,ed),8), esdf(seqen,ed,8) ) 
