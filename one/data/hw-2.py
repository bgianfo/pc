def speedup( seqn, par ):
  return seqn/par

def eff( speed, k ):
  return speed/k 

def esdf( seqn, par, k ):
  return (k*par-seqn)/(k*seqn-seqn)

seq   = 10926 

times = [
           [1  ,   11315.0   ],
           [2  ,    6194.0   ],
           [3  ,    4376.0   ],
           [4  ,    3543.0   ],
        ]

for i, dat in enumerate(times):
  s = 0
  ef = 0
  esd = 0

  try:
    s = speedup( seq, dat[1] )
    ef = eff( s, dat[0] )
    esd =  esdf( seq, dat[1], dat[0] )
  except:
    pass
  print "#%d  %s  %s  %s" % ( i, s, ef, esd ) 

print " \n\n"

seq   = 20966 
times = [
           [1  ,21530.0],
           [2  ,10841.0],
           [3  ,7669.0],
           [4  ,6155.0]
        ]        

for i, dat in enumerate(times):
  s = 0
  ef = 0
  esd = 0

  try:
    s = speedup( seq, dat[1] )
    ef = eff( s, dat[0] )
    esd =  esdf( seq, dat[1], dat[0] )
  except:
    pass
  print "#%d  %s  %s  %s" % ( i, s, ef, esd ) 

print " \n\n"

seq   = 35964 
times = [
           [1  ,   36366.0   ],
           [2  ,   18764.0   ],
           [3  ,   12383.0   ],
           [4  ,    9806.0   ],
        ]        
for i, dat in enumerate(times):
  s = 0
  ef = 0
  esd = 0

  try:
    s = speedup( seq, dat[1] )
    ef = eff( s, dat[0] )
    esd =  esdf( seq, dat[1], dat[0] )
  except:
    pass
  print "#%d  %s  %s  %s" % ( i, s, ef, esd ) 

print " \n\n"

seq   = 83368 
times = [
           [1  ,  81626.0   ],
           [2  ,  42750.0   ],
           [3  ,  27822.0   ],
           [4  ,  21164.0   ],
        ]        

for i, dat in enumerate(times):
  s = 0
  ef = 0
  esd = 0

  try:
    s = speedup( seq, dat[1] )
    ef = eff( s, dat[0] )
    esd =  esdf( seq, dat[1], dat[0] )
  except:
    pass
  print "#%d  %s  %s  %s" % ( i, s, ef, esd ) 



