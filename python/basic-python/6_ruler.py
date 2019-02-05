def draw_major(length, label=""):
    line = ('-' * length) + ' ' + label
    print(line)

def draw_minor(length):
    if (0 < length):
        draw_minor(length-1)  # recursive
        print('-' * length)   # draw ---
        draw_minor(length-1)  # recursive
    
def draw_ruler(labels):
    
    for l in labels:
        if (l != labels[-1]):
            draw_major(4, l)
            draw_minor(3)
    draw_major(4, labels[-1])

# test
if __name__ == '__main__':
    labels = ('a', str(22), 'kd', 'asdd')
    draw_ruler(labels)