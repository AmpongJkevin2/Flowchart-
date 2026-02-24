import os
import shutil

mappings = {
    "problemSet2": {
        "Percolation": ["Percolation.java", "Percolation.mmd"],
        "PercolationStats": ["PercolationStats.java", "PercolationStats.mmd"]
    },
    "problemSet3": {
        "Deque": ["Deque.java", "Deque.mmd"],
        "RandomizedQueue": ["RandomizedQueue.java", "RandomizedQueue.mmd"],
        "Permutation": ["Permutation.java", "Permutation.mmd"]
    },
    "problemSet4": {
        "Point": ["Point.java"], # MMD to be created
        "BruteCollinearPoints": ["BruteCollinearPoints.java", "BruteCollinearPoints.mmd"],
        "FastCollinearPoints": ["FastCollinearPoints.java", "FastCollinearPoints.mmd"]
    },
    "problemSet5": {
        "Board": ["Board.java", "Board.mmd"],
        "Solver": ["Solver.java", "Solver.mmd"]
    },
    "problemSet6": {
        "PointSET": ["PointSET.java", "PointSET.mmd"],
        "KdTree": ["KdTree.java", "KdTree.mmd"]
    }
}

base_dest = "finalAnswer"

# Move Files
for pset, probs in mappings.items():
    for prob, files in probs.items():
        dest_dir = os.path.join(base_dest, pset, prob)
        os.makedirs(dest_dir, exist_ok=True)
        for f in files:
            src = os.path.join(pset, f)
            if os.path.exists(src):
                shutil.copy(src, dest_dir)
            else:
                print(f"Warning: {src} not found")

# Handle PS2 Output
ps2_out = "problemSet2/output.txt"
if os.path.exists(ps2_out):
    dest_perc = os.path.join(base_dest, "problemSet2/Percolation/output.txt")
    dest_stats = os.path.join(base_dest, "problemSet2/PercolationStats/output.txt")
    shutil.copy(ps2_out, dest_stats)
    # Use same output for Percolation as placeholder/reference
    shutil.copy(ps2_out, dest_perc)

# Handle PS3 Output
ps3_out = "problemSet3/output.txt"
if os.path.exists(ps3_out):
    with open(ps3_out, 'r') as f:
        content = f.read()
    
    # Simple splitting based on headers observed
    # "--- Deque Output ---"
    # "--- RandomizedQueue Output ---"
    # "--- Permutation Output ---"
    
    deque_idx = content.find("--- Deque Output ---")
    rq_idx = content.find("--- RandomizedQueue Output ---")
    perm_idx = content.find("--- Permutation Output ---")
    
    if deque_idx != -1:
        end = rq_idx if rq_idx != -1 else len(content)
        deque_text = content[deque_idx:end].replace("--- Deque Output ---", "").strip()
        with open(os.path.join(base_dest, "problemSet3/Deque/output.txt"), 'w') as f: f.write(deque_text)
        
    if rq_idx != -1:
        end = perm_idx if perm_idx != -1 else len(content)
        rq_text = content[rq_idx:end].replace("--- RandomizedQueue Output ---", "").strip()
        with open(os.path.join(base_dest, "problemSet3/RandomizedQueue/output.txt"), 'w') as f: f.write(rq_text)

    if perm_idx != -1:
        perm_text = content[perm_idx:].replace("--- Permutation Output ---", "").strip()
        with open(os.path.join(base_dest, "problemSet3/Permutation/output.txt"), 'w') as f: f.write(perm_text)

# Handle PS4 Output
ps4_out = "problemSet4/output.txt"
if os.path.exists(ps4_out):
    shutil.copy(ps4_out, os.path.join(base_dest, "problemSet4/FastCollinearPoints/output.txt"))
    # BruteCollinearPoints output to be generated

# Handle PS5 Output
ps5_out = "problemSet5/output.txt"
if os.path.exists(ps5_out):
    shutil.copy(ps5_out, os.path.join(base_dest, "problemSet5/Solver/output.txt"))
    shutil.copy(ps5_out, os.path.join(base_dest, "problemSet5/Board/output.txt"))

# Handle PS6 Output
ps6_out = "problemSet6/output.txt"
if os.path.exists(ps6_out):
    shutil.copy(ps6_out, os.path.join(base_dest, "problemSet6/PointSET/output.txt"))
    shutil.copy(ps6_out, os.path.join(base_dest, "problemSet6/KdTree/output.txt"))

