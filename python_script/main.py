from copy import copy

import matplotlib.pyplot as plt
import matplotlib.patches as mpatches
import numpy as np
import csv


# Read the file & parse data into an array
def parseCSV(fileName):
    with open(fileName, newline='') as csvfile:

        reader = csv.reader(csvfile, delimiter=',', quotechar='|')

        # array to store 5 columns (s, mean, sd, low, high)
        data = np.zeros((5, getNumberOfRows(reader) - 1), dtype=int)

        # return file to first line
        csvfile.seek(0)

        # parsing data into array
        row_index = -1
        for row in reader:

            row_index += 1

            if row_index == 0:
                # print("index 0")
                continue

            data[0, row_index - 1] = row[0]
            data[1, row_index - 1] = row[1]
            data[2, row_index - 1] = row[2]
            data[3, row_index - 1] = row[3]
            data[4, row_index - 1] = row[4]

        csvfile.close()

        return data


def getNumberOfRows(dataReader):
    return sum(1 for row in dataReader)

def getX(data, entries):
    return data[0, :entries]


def getY(data, entries):
    return data[1, :entries]


def getSD(data, entries):
    return data[2, :entries]


def getYError(data, entries):
    return data[3:, :entries]

def getXFull(data):
    return data[0, :]


def getYFull(data):
    return data[1, :]


def getSDFull(data):
    return data[2, :]


def getYErrorFull(data):
    return data[3:, :]


def plot(plt, graphColor, errorColor, fileName, graphLegend = "5000", fullScope = False, entries = None):

    # parse data into [5][columns] -> s, mean, sd, low, high
    data = parseCSV(fileName)

    # collect points to plot by fullScope: either all, or 60 entries
    x_point = getXFull(data) if fullScope else getX(data, entries)
    y_point = getYFull(data) if fullScope else getY(data, entries)
    sd = getSDFull(data) if fullScope else getSD(data, entries)
    errorBounds = getYErrorFull(data) if fullScope else getYError(data, entries)

    markers, caps, bars = plt.errorbar(x_point, y_point, sd / 2, ecolor=errorColor)

    polygons = plt.fill_between(x_point, errorBounds[0], errorBounds[1])

    # edit polygons fill
    polygons.set_alpha(0.1)
    polygons.set_color(graphColor)

    # edit error bars
    [bar.set_alpha(0.7) for bar in bars]
    [bar.set_color(graphColor) for bar in bars]
    # [bar.set_linestyle('dashed') for bar in bars]
    [bar.set_linewidth(0.75) for bar in bars]

    # edit line
    markers.set_color(graphColor)
    markers.set_linewidth(3)

    return mpatches.Patch(color=graphColor, label='List size: ' + graphLegend)


def prettify(ax):
    ax.spines['right'].set_visible(False)
    ax.spines['top'].set_visible(False)
    ax.tick_params(axis='both', which='major', labelsize=12)
    ax.xaxis.label.set_size(fontsize=12)
    ax.yaxis.label.set_size(fontsize=12)

def arrange_xticks(ax, size):
    ax.set_xticks(np.arange(0, size + 1, 1 if size < 100 else 1000))
    xtick_lables = ax.get_xticklabels()
    if size < 100:
        for i in range(0, len(xtick_lables)):
            if i % 10 if size < 100 else 500 != 0:
                xtick_lables[i].set_visible(False)


def setLabels(ax, fig,
              suptitle='Impact of Shuffling on QuickSort Run Time '
                         '\n (Random Sequences with Repetition)',
              ylabel='Execution Time (milliseconds)',
              xlabel='Number of Random Shuffles'):
    fig.suptitle(suptitle, fontsize=14, fontweight='bold')
    ax.set_ylabel(ylabel)
    ax.set_xlabel(xlabel)


# Plot

# (fig1, fig2), (ax1, ax2) = plt.subplots(2, 2)
fig = plt.figure()

'''
RANGE 0-60 (REPETITION)
'''

# thirdPatch = plot(plt, '#21A0A0', '#19535F', "repetition3000.csv", "3000", False, 60)
# secondPatch = plot(plt, '#FCA311', '#B05A28', "repetition5000.csv", "5000", False, 60)
# firstPatch = plot(plt, '#FF3614', '#7D1D3F', "repetition10000.csv", "10000", False, 60)
#
#
# plt.legend(handles=[firstPatch, secondPatch, thirdPatch])
#
# axes1 = plt.axes()
#
# setLabels(axes1, fig)
# prettify(axes1)
# arrange_xticks(axes1, 70)
#
# plt.savefig('repetitionGraphQSort.png', dpi=300)
#
# plt.show()

'''
FULL RANGE (REPETITION)
'''

# thirdPatch = plot(plt, '#21A0A0', '#19535F', "repetition3000.csv", "3000", True)
# secondPatch = plot(plt, '#FCA311', '#B05A28', "repetition5000.csv", "5000", True)
# firstPatch = plot(plt, '#FF3614', '#7D1D3F', "repetition10000.csv", "10000", True)
#
#
# plt.legend(handles=[firstPatch, secondPatch, thirdPatch])
#
# axes1 = plt.axes()
#
# setLabels(axes1, fig)
# prettify(axes1)
# arrange_xticks(axes1, 10000)
#
# plt.savefig('repetitionGraphQSortFull.png', dpi=300)
#
# plt.show()

'''
RANGE 0-60 (NON-REPEATING)
'''

# thirdPatch1 = plot(plt, '#21A0A0', '#19535F', "nonRepeating3000.csv", "3000", False, 60)
# secondPatch1 = plot(plt, '#FCA311', '#B05A28', "nonRepeating5000.csv", "5000", False, 60)
# firstPatch1 = plot(plt, '#FF3614', '#7D1D3F', "nonRepeating10000.csv", "10000", False, 60)
#
#
# plt.legend(handles=[firstPatch1, secondPatch1, thirdPatch1])
#
# axes2 = plt.axes()
#
# setLabels(axes2, fig, suptitle='Impact of Shuffling on QuickSort Run Time\n(Sequences without Repetition)')
# prettify(axes2)
# arrange_xticks(axes2, 70)
#
# plt.savefig('noRepetitionGraphQSort.png', dpi=300)
#
# plt.show()

'''
FULL RANGE (NON-REPEATING)
'''

# thirdPatch1 = plot(plt, '#21A0A0', '#19535F', "nonRepeating3000.csv", "3000", True)
# secondPatch1 = plot(plt, '#FCA311', '#B05A28', "nonRepeating5000.csv", "5000", True)
# firstPatch1 = plot(plt, '#FF3614', '#7D1D3F', "nonRepeating10000.csv", "10000", True)
#
#
# plt.legend(handles=[firstPatch1, secondPatch1, thirdPatch1])
#
# axes2 = plt.axes()
#
# setLabels(axes2, fig, suptitle='Impact of Shuffling on QuickSort Run Time\n(Sequences without Repetition)')
# prettify(axes2)
# arrange_xticks(axes2, 10000)
#
# plt.savefig('noRepetitionGraphQSortFull.png', dpi=300)
#
# plt.show()
