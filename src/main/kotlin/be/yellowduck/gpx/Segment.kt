package be.yellowduck.gpx

/**
 * A segment is a sorted list of waypoints.
 *
 * @property points The list of TrackPoints in this segment
 *
 * @since v1.0.0
 */
data class Segment(
    val points: MutableList<TrackPoint> = mutableListOf()
) {

    /**
     * Returns the total distance of the segment
     */
    val distance: Distance
        get() {
            var distance = 0.0
            points.forEachIndexed { index, point ->
                if (index > 0) {
                    distance += points[index - 1].distanceTo(point).meters
                }
            }
            return Distance(distance)
        }

    /**
     * Returns the segment as a encoded polyline string.
     *
     * [Encoded Polyline Algorithm Format](https://developers.google.com/maps/documentation/utilities/polylinealgorithm)
     *
     * @return A string with the encoded polyline
     */
    fun toPolyline(): String {
        return Polyline.encode(points)
    }

}
