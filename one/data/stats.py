
def speedup( seq, par ):
  return seq/par

def eff( speed, k ):
  return speed/k 

def esdf( seq, par, k ):
  return (k*par-seq)/(k*seq-seq)

seqen = 1412430.0

one   = 1281861.0
two   = 641332.0
four  = 320997.0
eight = 162863.0

# Elementary CA
print "#1 %s %s " % ( speedup( seqen, one ), eff( speedup( seqen, one ), 1 ) )
#print "#2 %s %s %s " % ( speedup( seqen, two), eff( speedup( seqen, two), 2 ), esdf( seqen, two, 2 ) )
#print "#4 %s %s %s " % ( speedup( seqen, four), eff( speedup( seqen, four), 4 ), esdf( seqen, four, 4 ) )
#print "#8 %s %s %s " % ( speedup( seqen, eight), eff( speedup( seqen, eight), 8 ), esdf( seqen, eight, 8 ) )

seqen = 405010.0
eight = 51014.0
print "#8 %s %s " % ( speedup( seqen, eight ), eff( speedup( seqen, eight), 8 ) )

seqen = 54571.0
eight = 54614.0
print "#8 %s %s " % ( speedup( seqen, eight ), eff( speedup( seqen, eight), 8 ) )
